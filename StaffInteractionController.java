package healthcareLook;

/*
 * MAY NEED REVISION!
 */
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
/*
 * the purpose of this window is for the staff to set appointments as well
 * this is more for the patients who have no doctor they request, so any doctor can select these patients.
 */
public class StaffInteractionController implements Initializable {

	
	

	@FXML
	Button submit;
	@FXML
	ListView<String> list;
	
	String idNumber;
	
	Connection conn;
	Statement sqlState;
	ResultSet rows;
	ResultSet rows2;
	boolean wasfound = false;
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	Alert warnAlert = new Alert(AlertType.WARNING);
	Optional<ButtonType> result;
	
	
	ArrayList<Appointment> list2 = new ArrayList<Appointment>();
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//I retrieve the id number.
		idNumber = StaffInteractionWindow.getID(idNumber);
		
		ObservableList<String> patients = FXCollections.observableArrayList();
		//This opens a connection to the database.
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "select * from appointment";
			String command2 = "select first_name, last_name from employee where staff_id = " + idNumber;
			//i retrieve the 
			Statement sql2 = conn.createStatement();
			Statement sql3 = conn.createStatement();
			rows = sqlState.executeQuery(command);
			rows2 = sql3.executeQuery(command2);
			ArrayList<ResultSet> rowinfinite = new ArrayList<ResultSet>();
			int counter = 0;
	//		System.out.println("here we go " + idNumber);
			//This checks if any patients have requested the doctor, if so then they are on the first of the listview
			//since the receptionist can already schedule the appointment this part may not be needed.
			while(rows.next()){
		//		System.out.println(rows.getString(1));
		//		System.out.println(rows.getString(2));
		//		System.out.println(rows.getString(3));
		//		System.out.println(rows.getString(4));
				String docToCheck = rows.getString(5);
		//		System.out.println(docToCheck);
		//		System.out.println(rows.getString(6));
				
				
		//		System.out.println("Inside loop # 1");
				command2 = "select first_name, last_name from employee where staff_id = " + idNumber;
				rowinfinite.add(counter, sql2.executeQuery(command2));
		//		System.out.println("command given");
				if(rowinfinite.get(counter).next()){
		//			System.out.println(rowinfinite.get(counter).getString(1));
		//			System.out.println(rowinfinite.get(counter).getString(2));
					
					
					
		//			System.out.println("Inside loop #2");
					if(docToCheck != null && !docToCheck.isEmpty()){
		//				System.out.println("doctor to check was not null!");
						if(docToCheck.equals(rowinfinite.get(counter).getString(1)) || docToCheck.equals(rows2.getString(2))){
		//					System.out.println("Now Addding!");
							patients.add(rows.getString(3));
							list2.add(new Appointment(rowinfinite.get(counter).getString(1), rowinfinite.get(counter).getString(2),rowinfinite.get(counter).getString(3),rowinfinite.get(counter).getString(4)));
						}
					}
					else{
		//				System.out.println("Doc to check was null :( ");
					}
				}
			}
			command = "select * from appointment";
			ResultSet rows3 = sqlState.executeQuery(command);
			
			while(rows3.next()){
				
		//		System.out.println("last loop!");
				for(int i=0; i < patients.size(); i++){
		//			System.out.println("inside if statment! was not found!");
					wasfound = false;
					if(patients.get(i).equals(rows3.getString(3))){
		//				System.out.println("it was found!");
						wasfound = true;
					}
					
				}
				
				if(!wasfound){
		//			System.out.println("Will this add?!");
					String addrow = rows3.getString(6);
		//			System.out.println("do you have a doctor? " + addrow);
					if(addrow != null && !addrow.isEmpty()){
						
					}
				
					else{
						//I add the information about the appointment to an arraylist as well as adding it to the patient observable list.
						patients.add(rows3.getString(3));
						list2.add(new Appointment(rows3.getString(1), rows3.getString(3),rows3.getString(2),rows3.getString(4)));
					}
				}
				wasfound = false;
				
			}

			conn.close();
		//	System.out.println("Guess i am done :) ");
			
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		if(patients.isEmpty()){
			dataAlert.setTitle("NO PATIENTS AVAILABLE!");
			dataAlert.setHeaderText("All patients are taken care of.");
			dataAlert.setContentText("Please wait for new patients to come in");
			dataAlert.showAndWait();

		}
		//I fill the listview with the patients.
		list.setItems(patients);
		list.getSelectionModel().select(1);
		submit.setOnAction(e->{
			//This confirms the person wants to select this patient.
		//	System.out.println("Confirm please?!");
			confirmAlert.setTitle("Confirmation");
			confirmAlert.setHeaderText("Is this a condition you can treat? Are you sure you want this patient?");
			confirmAlert.setContentText("Date: " + list2.get(list.getSelectionModel().getSelectedIndex()).getAppointmentDate() + "\nTime: " + list2.get(list.getSelectionModel().getSelectedIndex()).getAppointmentTime());
			
			result = confirmAlert.showAndWait();;
			
			if(result.get() == ButtonType.OK){
				
				idNumber = StaffInteractionWindow.getID(idNumber);
				//checks of the patient is available on that day.
				boolean check = DatabaseWork.CheckAvailability(list2.get(list.getSelectionModel().getSelectedIndex()).getAppointmentTime(), idNumber, list2.get(list.getSelectionModel().getSelectedIndex()).getAppointmentDate());
				if(check){
					
				//if the patient was available that day then the database updates with the information.
					boolean isOk = DatabaseWork.UpdateAppointment(idNumber, list2.get(list.getSelectionModel().getSelectedIndex()).getPatient_id(),list2.get(list.getSelectionModel().getSelectedIndex()).getAppointmentDate(),list2.get(list.getSelectionModel().getSelectedIndex()).getAppointmentTime());
			
					if(isOk){
					
					dataAlert.setTitle("Appointment Approved!");
					dataAlert.setHeaderText("Appointment is confirmed");
					dataAlert.setContentText("Thank you for choosing this patient.");
					dataAlert.showAndWait();
					//Remove the item so it can be checked again.
					list.getItems().remove(list.getSelectionModel().getSelectedIndex());
					
					}
				
				}
				else{
					// an error message in case the appointment can no happen then.
					warnAlert.setTitle("Oops! There Was A Problem");
					warnAlert.setHeaderText("Warning!");
					warnAlert.setContentText("You can not have this appointment at this time");
					warnAlert.showAndWait();
					confirmAlert.close();
				}
			}
			else{

				confirmAlert.close();
			}
			
			
			
		});
		
		
		
		
		
	}

}


