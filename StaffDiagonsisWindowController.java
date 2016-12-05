package healthcareLook;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/*
 * The purpose of this window is to allow the staff member to set diagnosis
 * of the patients they saw that day.
 * this also will create a bill for the patient to pay.
 */
public class StaffDiagonsisWindowController implements Initializable {
	
	@FXML
	Label complaintLabel;
	@FXML
	Label noteLabel;
	@FXML
	ListView patientView;
	@FXML
	ListView diagnosisView;
	@FXML
	Button confirmButton;

	
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	Alert warnAlert = new Alert(AlertType.WARNING);
	Optional<ButtonType> result;
	
	Connection conn;
	Statement sqlState;
	Statement sqlState2;
	Statement sqlState3;
	Statement sqlState5;
	PreparedStatement sqlState4;
	String staffId, patId;
	boolean isInPayment = false;
	String patientCheck = "";
	int indexSelected;
	String cost;
	int notUsed;
	
	
	ArrayList<DiagnosisData> diaData = new ArrayList<DiagnosisData>();
	ArrayList<Appointment> appData = new ArrayList<Appointment>();
	ArrayList<Appointment> appDatacheck = new ArrayList<Appointment>();
	ArrayList<String> patidData = new ArrayList<String>();
	ArrayList<String> diadata = new ArrayList<String>();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//first we retrieve the id.
		staffId = StaffDiagonsisWindow.RetrieveID(staffId);
		
		ObservableList<String> patientList = FXCollections.observableArrayList();
		ObservableList<String> diagnosisList = FXCollections.observableArrayList();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			sqlState2 = conn.createStatement();
			sqlState3 = conn.createStatement();
			String command = "Select diagnosis, cost from diagnosis_chart ";
			String command4 = "Select appointment_date, appointment_time, complaint, patient_id from appointment where requested_doctor = '" + staffId + "' and nurse_visit = 'Y' and doctor_visit is NULL";
			String command5 ="Select appointment_date, appointment_time, complaint, patient_id from appointment where requested_doctor is NULL and nurse_visit = 'Y' and doctor_visit is NULL";
		//	System.out.println("We are before result set" + staffId);
			ResultSet row01 = sqlState.executeQuery(command);
			
		//	System.out.println("We are after result set");
			// gets the diagnosis from the diagnosis table
			
			while(row01.next()){
				diagnosisList.add(row01.getString(1));
		//		System.out.println("loop #1");
				diaData.add(new DiagnosisData(row01.getString(1), row01.getString(2)));
		//		System.out.println(row01.getString(1) + " " + row01.getString(2));
				
			}
			ResultSet rows2 = sqlState2.executeQuery(command4);
			while(rows2.next()){
				patientView.getItems().add(rows2.getString(1) + " - " +rows2.getString(2) + " - " +rows2.getString(4) + " - " +rows2.getString(3));
				
			}
			ResultSet rows3 = sqlState3.executeQuery(command5);
			
			while(rows3.next()){
				patientView.getItems().add(rows3.getString(1) + " - " +rows3.getString(2) + " - " +rows3.getString(4) + " - " +rows3.getString(3));
			}
		}

		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		diagnosisView.setItems(diagnosisList);
		//patientView.setItems(patientList);
		
		diagnosisView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//This will change the label to the complaint of the id number you have selected.
		patientView.setOnMouseClicked(e->{
			//This array will store the information from the selected index. I split it so I can retrieve the information I want.
			String[] split = null;
			indexSelected = patientView.getSelectionModel().getSelectedIndex();
			split = patientView.getSelectionModel().getSelectedItem().toString().split(" - ");
			//The complaint of the patient is on array index 3
			complaintLabel.setText(split[3]);
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
				Statement noteStatement = conn.createStatement();
				String noteCommand = "Select height, weight, heart_rate, blood_pressure, medication from doctor_notes where appointment_date = '"+ split[0] + "' and appointment_time = '" + split[1] + "' and patient_id = '" + split[2] + "'";
				ResultSet noteResult = noteStatement.executeQuery(noteCommand);
				
				while(noteResult.next()){
					
					noteLabel.setText("Height: " + noteResult.getString(1) + "\tWeight: " + noteResult.getString(2) + "\nHeart_Rate: " + noteResult.getString(3) + "\tBlood_pressure: " + noteResult.getString(4) + "\nMedication: " + noteResult.getString(5));
					
				}
				
				
			}catch(SQLException ex){
				System.out.println("SQLException : " +ex.getMessage());
				System.out.println("VendorError : " +ex.getErrorCode());
			}
			catch(ClassNotFoundException j){
				j.printStackTrace();
			}
		});
	
		
		
		//This will ask if the person wishes to add another diagnosis to the patient compliant. If so then it 
		//keeps the information in the list and add the diagnosis to the payment_bill table otherwise it will
		//remove the id number (associated with the complaint) from the list and save to the payment_bill table.
		confirmButton.setOnAction(g->{
			//The same for changing the label is used here for this confirmation messagebox.
			String[] split = null;
			split = patientView.getSelectionModel().getSelectedItem().toString().split(" - ");
		//	System.out.println("Still working?");
			confirmAlert.setTitle("Confirmation");
			confirmAlert.setHeaderText("Is the following diagnosis correct?");
			confirmAlert.setContentText("Complaint: " + split[3] + "\nDiagnosis: " + diagnosisView.getSelectionModel().getSelectedItems().toString());
			
			result = confirmAlert.showAndWait();
			//If It was the right diagnosis then it will go below
			if(result.get() == ButtonType.OK){
				diadata.addAll(diagnosisView.getSelectionModel().getSelectedItems());

				try {
					
					//This will select cost from the diagnosis chart this is needed so I can fill the payment_bill database.
					String command4;
					String command5;
					String[] datetimeid = patientView.getSelectionModel().getSelectedItem().toString().split(" - ");
					for(int i = 0; i < diadata.size(); i++){
						
						sqlState5 = conn.createStatement();
						command5 ="select cost from diagnosis_chart where diagnosis = '" + diadata.get(i) +"'";
						
						ResultSet row5 = sqlState5.executeQuery(command5);
						if(row5.next()){
							cost = row5.getString(1);
						}
						//This is where I insert the new bill into the payment_bill table.
						command4 = "insert into payment_bill(diagnosis, appointment_date, appointment_time, payment, patient_id, paid) values(?, ?, ?, ?, ?, ?)";
						sqlState4 = conn.prepareStatement(command4);
						sqlState4.setString(1, diadata.get(i));
						sqlState4.setString(2, datetimeid[0]);
						sqlState4.setString(3, datetimeid[1]);
						sqlState4.setString(4, cost);
						sqlState4.setString(5, datetimeid[2]);
						sqlState4.setString(6, "N");
						
						notUsed = sqlState4.executeUpdate();
						//I make this a for loop because the person may have multiple diagnosis during one doctor visit.
						
						
					}
					//After the for loop I consider the doctor visit complete and update the appointment table tuple.
					String command6 = "update appointment set doctor_visit = 'Y' where appointment_date = '" + datetimeid[0] + "' and appointment_time = '" + datetimeid[1] + "' and patient_id = '" + datetimeid[2] +"'";
					
					sqlState5.executeUpdate(command6);
				}
				catch(SQLException ex){
					System.out.println("SQLException : " +ex.getMessage());
					System.out.println("VendorError : " +ex.getErrorCode());
				}
		
				
				//Now I delete the patient id from the listview to ensure that the id and complaint is never used again.
				//This prevents a person from being charged twice for one diagnosis.
				patientView.getItems().remove(patientView.getSelectionModel().getSelectedIndex());
				dataAlert.setTitle("Doctor Visit Result");
				dataAlert.setHeaderText("The Appointment is now completed and a bill has been processed");
				dataAlert.showAndWait();
				
				
			}
			else{
				confirmAlert.close();
			}
			
		});
		
		
	}

}
