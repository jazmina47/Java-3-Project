package healthcareLook;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CheckinWindowController implements Initializable{
	
	@FXML
	ListView list;
	@FXML
	Button submit;
	@FXML
	Button cancel;
	Alert warnAlert = new Alert(AlertType.WARNING);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	Connection conn;
	Statement sqlState;
	Statement sqlState2;
	ResultSet rows;
	String patid;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		patid = CheckinWindow.getID();
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
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
				
			}
			
			submit.setOnAction(e->{
				if(list.getSelectionModel().isEmpty()){
					warnAlert.setTitle("Warning!");
					warnAlert.setHeaderText("No appointment selected");
					warnAlert.setContentText("Please select an appointment or hit the cancel button.");
					warnAlert.showAndWait();
				}else{
					String[] dateandtime = list.getItems().get(list.getSelectionModel().getSelectedIndex()).toString().split(" @ ");
					String command2 = "update appointment set checkin = 'Y' where appointment_date = '" + dateandtime[0] + "' and appointment_time = '" + dateandtime[1] +"' and patient_id = '" + patid +"'";
					try {
						sqlState.executeUpdate(command2);
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					dataAlert.setTitle("Checked in");
					dataAlert.setHeaderText("Check in result:");
					dataAlert.setContentText("You have checked in successfully");
					list.getItems().remove(list.getSelectionModel().getSelectedIndex());
					dataAlert.showAndWait();
					
				}
			});
			cancel.setOnAction(f->{
				CheckinWindow.stageClose();
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
