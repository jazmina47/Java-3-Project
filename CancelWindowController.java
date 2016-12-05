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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class CancelWindowController implements Initializable{
	
	
	@FXML
	ListView list;
	@FXML
	Button submit;
	@FXML
	Button cancel;
	Alert warnAlert = new Alert(AlertType.WARNING);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	Connection conn;
	Statement sqlState;
	Statement sqlState2;
	PreparedStatement prepare;
	ResultSet rows;
	String patid;
	Optional<ButtonType> result;
	boolean closewindow = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		patid = CancelWindow.getID();
	//	System.out.println(patid);
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			sqlState2 = conn.createStatement();
			
			String command = "Select appointment_date, appointment_time from appointment where patient_id = '" + patid + "' And checkin = 'N'";
		//	System.out.println("has the command for appointment!");
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
		//	System.out.println("Any appointments?");
			while(rows.next()){
		//		System.out.println("appointment found");
				list.getItems().add(rows.getString(1) + " @ " + rows.getString(2));
			}
			
			if(list.getItems().isEmpty()){
				dataAlert.setTitle("No Appointments");
				dataAlert.setHeaderText("No appointments to checkin");
				dataAlert.setContentText("Please wait for a patient to make an appointment.");
				dataAlert.showAndWait();
				closewindow = true;
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
					confirmAlert.setContentText("Did the person cancel or not show up?");
					ButtonType cancel = new ButtonType("Cancel");
					ButtonType noshow = new ButtonType("No Show");
					String[] dateandtime = list.getItems().get(list.getSelectionModel().getSelectedIndex()).toString().split(" @ ");
					confirmAlert.getButtonTypes().setAll(cancel, noshow);
					result = confirmAlert.showAndWait();
					if(result.get() == cancel){
					
						String command2 = "update appointment set checkin = 'C', nurse_visit = 'C', doctor_visit 'C' where appointment_date = '" + dateandtime[0] + "' and appointment_time = '" + dateandtime[1] +"' and patient_id = '" + patid +"'";
						try {
							sqlState.executeUpdate(command2);
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
						dataAlert.setTitle("Checked in");
						dataAlert.setHeaderText("Canceled appointment result:");
						dataAlert.setContentText("You have canceled the appointment successfully");
						dataAlert.showAndWait();
						
					}
					else{
						String command2 = "update appointment set checkin = 'NS', nurse_visit = 'NS', doctor_visit  = 'NS' where appointment_date = '" + dateandtime[0] + "' and appointment_time = '" + dateandtime[1] +"' and patient_id = '" + patid +"'";
						try {
							prepare = conn.prepareStatement("insert into payment_bill (diagnosis, appointment_date, appointment_time, payment, patient_id, paid)" + "values(?, ?, ?, ?, ?, ?)");
							prepare.setString(1,"no show" );
							prepare.setString(2, dateandtime[0]);
							prepare.setString(3, dateandtime[1]);
							prepare.setString(4,"75" );
							prepare.setString(5, patid);
							prepare.setString(6, "N");
						} catch (Exception e2) {
							
							e2.printStackTrace();
						}
						try {
							sqlState.executeUpdate(command2);
							prepare.executeUpdate();
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						dataAlert.setTitle("Checked in");
						dataAlert.setHeaderText("Canceled appointment result:");
						dataAlert.setContentText("You have listed the appointment as 'No Show' the patient will have a bill for not canceling.");
						dataAlert.showAndWait();
						CancelWindow.stageClose();
					}
				}
			});
			cancel.setOnAction(f->{
				CancelWindow.stageClose();
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
