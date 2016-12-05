package healthcareLook;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class DoctorCancelWindowController implements Initializable{

	
	@FXML
	ListView list;
	@FXML
	Button submit;
	@FXML
	Button cancel;
	Alert warnAlert = new Alert(AlertType.WARNING);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	Optional<ButtonType> result;

	Connection conn;
	Statement sqlState;
	PreparedStatement prepare;
	ResultSet rows;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select appointment_date, appointment_time, patient_id from appointment where nurse_visit = 'Y' And doctor_visit is NULL";
	//		System.out.println("has the command for appointment!");
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
	//		System.out.println("Any appointments?");
			while(rows.next()){
	//			System.out.println("appointment found");
				list.getItems().add(rows.getString(1) + " - " + rows.getString(2) + " - " + rows.getString(3));
			}
			
			if(list.getItems().isEmpty()){
				warnAlert.setTitle("No Appointments");
				warnAlert.setHeaderText("No appointments mark as no show");
				warnAlert.setContentText("Please wait for a patient to make an appointment.");
				warnAlert.showAndWait();
			
			}
			submit.setOnAction(e->{
				
				
				if(list.getSelectionModel().isEmpty()){
					warnAlert.setTitle("Warning!");
					warnAlert.setHeaderText("No appointment selected");
					warnAlert.setContentText("Please select an appointment or hit the cancel button.");
					warnAlert.showAndWait();
				}else{
					confirmAlert.setTitle("Cancel or no show");
					confirmAlert.setHeaderText("Please Confirm the following:");
					confirmAlert.setContentText("Did the person really not show up?");
					ButtonType showup = new ButtonType("showed up");
					ButtonType noshow = new ButtonType("No Show");
					String[] dateandtime = list.getItems().get(list.getSelectionModel().getSelectedIndex()).toString().split(" - ");
					confirmAlert.getButtonTypes().setAll(showup, noshow);
					result = confirmAlert.showAndWait();
					if(result.get() == showup){
					
						confirmAlert.close();
						
					}
					else{
						String command2 = "update appointment set checkin = 'Y', nurse_visit = 'Y', doctor_visit  = 'NS' where appointment_date = '" + dateandtime[0] + "' and appointment_time = '" + dateandtime[1] +"' and patient_id = '" + dateandtime[2] +"'";
						try {
							prepare = conn.prepareStatement("insert into payment_bill (diagnosis, appointment_date, appointment_time, payment, patient_id, paid)" + "values(?, ?, ?, ?, ?, ?)");
							prepare.setString(1,"no show" );
							prepare.setString(2, dateandtime[0]);
							prepare.setString(3, dateandtime[1]);
							prepare.setString(4,"75" );
							prepare.setString(5, dateandtime[2]);
							prepare.setString(6, "N");
							sqlState.executeUpdate(command2);
							prepare.executeUpdate();
						} catch (Exception e2) {
							
							e2.printStackTrace();
						}
						
						dataAlert.setTitle("Checked in");
						dataAlert.setHeaderText("Canceled appointment result:");
						dataAlert.setContentText("You have listed the appointment as 'No Show' the patient will have a bill for not canceling.");
						dataAlert.showAndWait();
						DoctorCancelWindow.stageClose();
			
					}
				}
		
		});
		
		cancel.setOnAction(f->{
			DoctorCancelWindow.stageClose();
		});
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

}
