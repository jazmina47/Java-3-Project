package healthcareLook;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;

public class NurseNotesWindowController implements Initializable{
	
	@FXML
	TextField height;
	@FXML
	TextField weight;
	@FXML
	TextField heartRate;
	@FXML
	TextField bloodPressure;
	@FXML
	TextField medication;
	@FXML
	Button submit;
	@FXML
	Button cancel;
	@FXML
	ListView list;
	Alert warnAlert = new Alert(AlertType.WARNING);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	String staffID;
	Connection conn;
	Statement sqlState;
	PreparedStatement prepare;
	ResultSet rows;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		staffID = NurseNotesWindow.getID();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select appointment_date, appointment_time, patient_id from appointment where checkin = 'Y' And nurse_visit is NULL";
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
				warnAlert.setHeaderText("No appointments to checkin");
				warnAlert.setContentText("Please wait for a patient to make an appointment.");
				warnAlert.showAndWait();
			
			}
		
	
			submit.setOnAction(e->{
				if(height.getText().trim().isEmpty() || weight.getText().trim().isEmpty() || heartRate.getText().trim().isEmpty() || bloodPressure.getText().trim().isEmpty()){
					warnAlert.setTitle("Warning!");
					warnAlert.setHeaderText("Field was left empty");
					warnAlert.setContentText("Please fill in all fields. \nMedication can be left blank if the patient has no medications.");
					warnAlert.showAndWait();
				}
				else{
					String[] dateTimeID = list.getSelectionModel().getSelectedItem().toString().split(" - ");
					//save to some database for notes;
					//This is to allow the doctor to know the patient has seen a nurse.
					try {
						prepare = conn.prepareStatement("insert into doctor_notes (appointment_date, appointment_time, patient_id, height, weight, heart_rate, blood_pressure, medication)" + "values(?,?,?,?,?,?,?,?)");
						prepare.setString(1, dateTimeID[0]);
						prepare.setString(2, dateTimeID[1]);
						prepare.setString(3, dateTimeID[2]);
						prepare.setString(4, height.getText().trim());
						prepare.setString(5, weight.getText().trim());
						prepare.setString(6, heartRate.getText().trim());
						prepare.setString(7, bloodPressure.getText().trim());
						if(medication.getText().trim().isEmpty()){
							prepare.setString(8, "none");
						}
						else{
						prepare.setString(8, medication.getText().trim());
						}
						prepare.executeUpdate();
						
						
						dataAlert.setTitle("Nurse checkup finished");
						dataAlert.setHeaderText("Nurse has finished with the patient.");
						dataAlert.setContentText("Please inform the patient to wait for the doctor to come");
						dataAlert.showAndWait();
						
						String command2 = "update appointment set nurse_visit = 'Y' where appointment_date = '" + dateTimeID[0] + "' and appointment_time = '" + dateTimeID[1] + "' and patient_id = '" + dateTimeID[2] +"'";
						sqlState.executeUpdate(command2);
						list.getItems().remove(list.getSelectionModel().getSelectedIndex());
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					
						
					
					
				}
				
			});
			cancel.setOnAction(f->{
				NurseNotesWindow.stageClose();
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
