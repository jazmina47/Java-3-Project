package healthcareLook;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

//Last window patient should get to.
public class PatientInteractionWindowController implements Initializable{

	
	
	@FXML
	TextField complaint;
	@FXML
	DatePicker appointmentDate;
	@FXML
	Button submit;
	@FXML
	ImageView dateError;
	@FXML
	ImageView complaintError;

	@FXML
	ListView<String> list;

	
	
	String dateString,docString,complaintString, idNumber, date;
	String staffID = "";
	Tooltip appointmenttip;
	boolean check;
	
	TextInputDialog confirmAlert = new TextInputDialog("");
	Optional<String> result;
	Optional<ButtonType> result2;
	Alert conAlert = new Alert(AlertType.CONFIRMATION);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	Alert warnAlert = new Alert(AlertType.WARNING);
	boolean safe = false;
	Connection conn;
	Statement sqlState;
	Statement sqlState2;
	Statement sqlState3;
	Statement sqlState4;
	ResultSet rows;
	ResultSet rows2;
	ResultSet rows3;
	ResultSet rows4;
	
	ArrayList<String> updateTimes = new ArrayList<String>();
	ArrayList<String> resetTimes = new ArrayList<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//I set up the list to have the times of the day we are open.
		ObservableList<String> times = FXCollections.observableArrayList("8:00 am","9:00 am","10:00 am","11:00 am","12:00 pm","1:00 pm","2:00 pm","3:00 pm","4:00 pm",
				"5:00 pm");
		resetTimes.addAll(times);
		appointmentDate.setValue(LocalDate.now());
		date = appointmentDate.getValue().toString();
		//This checks if you have a doctor you want to see or not.
		confirmAlert.setTitle("Requested Doctor");
		confirmAlert.setHeaderText("Do you have a doctor you wish to see?\nPlease enter the first or last name of the doctor");
		confirmAlert.setContentText("Doctor's first or last name: ");
		result = confirmAlert.showAndWait();
		//if they entered something it will open a connection to the database.
		if(result.isPresent()){
			if(result.get().trim().equals("")){
				confirmAlert.close();
			//	System.out.println("Adding times...");
				list.getItems().addAll(times);
				list.getSelectionModel().select(3);
				
			}
			else{
				try{
					Class.forName("com.mysql.jdbc.Driver");

					conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
					sqlState = conn.createStatement();
				//	System.out.println("Command 1");
					String command = "Select first_name, last_name from employee where first_name = '" + result.get() +"' OR last_name = '" + result.get() +"'";
					
					rows = sqlState.executeQuery(command);
					
					while(rows.next()){
					//while the row has more names it will ask if this is the doctor you meant.	
						conAlert.setTitle("Confirmation");
						conAlert.setHeaderText("Is this the doctor's name below?");
						conAlert.setContentText("First Name: " + rows.getString(1) +"\nLast Name: " +rows.getString(2));
						
						ButtonType yes = new ButtonType("YES");
						ButtonType no = new ButtonType("NO");
						
						conAlert.getButtonTypes().setAll(yes, no);
						result2 = conAlert.showAndWait();
						//if you select yes...
						if(result2.get() == yes){
							//Get appointment schedule of doctor
							
							try{
								
						//		System.out.println("Command 2");
								sqlState3 = conn.createStatement();
								String command3 = "Select staff_id from employee where first_name = '" + rows.getString(1) + "' AND last_name = '" + rows.getString(2) + "'";
								rows3 = sqlState3.executeQuery(command3);
								
								if(rows3.next()){
									staffID = rows3.getString(1);
								}
								sqlState2 = conn.createStatement();
								
							//	System.out.println("Command 3");
								String command2 = "Select appointment_time from appointment where staff_id = '" + staffID  + "' AND appointment_date = '" + date + "'";
							//	System.out.println("I have the command!");
								rows2 = sqlState2.executeQuery(command2);
								//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
							//	System.out.println("Did I find something?!");
								updateTimes.clear();
								updateTimes.addAll(resetTimes);
								while(rows2.next()){
									//this checks if the doctor has an appointment on that date and time.
								//	System.out.println("Looking for availablilty");
									for(int i = 0; i < updateTimes.size(); i++){
										if(rows2.getString(1).equals(updateTimes.get(i))){
											//Replace the normal time to say it was taken.
											updateTimes.set(i, (updateTimes.get(i) + " Not Available"));
											
										}
									}
									
								}
								
								list.getItems().addAll(updateTimes);

							
							}
							catch(SQLException ex){
								System.out.println("SQLException : " +ex.getMessage());
								System.out.println("VendorError : " +ex.getErrorCode());
							}
							
							
						}
						//otherwise no was selected and the next option is loaded.
						else{
							list.getItems().addAll(times);
						}
						
						
					}

				
					
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
		else{
		
		
			//the updated times are added to the listview. I put the selection at item number 3.
		//	System.out.println("Adding times down below...");
			list.getItems().addAll(times);
			list.getSelectionModel().select(3);
		}
		
		appointmentDate.setOnAction(j->{
			//IF you had a doctor you wanted, each time you select a new date
			//this will check that date and see if there is appointments on that day
			//or not.
			if(!staffID.equals("")){
				date = appointmentDate.getValue().toString();
				try{
					Class.forName("com.mysql.jdbc.Driver");
					conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );

					sqlState4 = conn.createStatement();
					
			//		System.out.println("Command 4");
					String command2 = "Select appointment_time from appointment where staff_id = '" + staffID  + "' AND appointment_date = '" + date + "'";
			//		System.out.println("I have the command!");
					rows4 = sqlState4.executeQuery(command2);
					//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
			//		System.out.println("Did I find something?!");
					updateTimes.clear();
					updateTimes.addAll(resetTimes);
					while(rows4.next()){
				//		System.out.println("row4 = : " + rows4.getString(1));
				//		System.out.println("times = : " + times);
						for(int i = 0; i < times.size(); i++){
							
							if(rows4.getString(1).equals(updateTimes.get(i))){
								//Replace the normal time to say it was taken.
								
								updateTimes.set(i, (updateTimes.get(i) + " Not Available"));
							//	updateTimes.add(updateTimes.get(i));
								
							}
						}
						
					}
					
					
			//		System.out.println(updateTimes);
					list.getSelectionModel().clearSelection();
					list.getItems().clear();
		
					
					list.getItems().addAll(updateTimes);
					list.getSelectionModel().select(3);
				
				}
				catch(SQLException ex){
					System.out.println("SQLException : " +ex.getMessage());
					System.out.println("VendorError : " +ex.getErrorCode());
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
		
		//this will save the appointment date
		//the patient will be given a card with the time and date on it.
		submit.setOnAction(e->{
			check = true;
			
			if(list.getSelectionModel().isEmpty()){
				dateError.setVisible(true);
				appointmenttip.install(dateError, new Tooltip("Please enter in a time."));
				check = false;
			}
			
			if(appointmentDate.getValue().equals("")){
				dateError.setVisible(true);
				appointmenttip.install(dateError, new Tooltip("Please enter in a date."));
				check = false;
			}
			else{
				dateError.setVisible(false);
			}
			
			if(complaint.getText().trim().equals("")){
				complaintError.setVisible(true);
				appointmenttip.install(complaintError, new Tooltip("Please enter your complaint."));
				check = false;
			}
			else{
				complaintError.setVisible(false);
			}
			
			if(check){
				
				idNumber = PatientInteractionWindow.GetID(idNumber);
				String appointment = appointmentDate.getValue().toString();
				String doctor;
				if(staffID.trim().equals("")){
					doctor = "";
					//check if the patient has no appointment that day.
					if(DatabaseWork.CheckAvailabilityPatient(list.getSelectionModel().getSelectedItem().toString(), idNumber, appointment)){
					//save the appointment information into the database
						safe = DatabaseWork.SetAppointmentPatient(idNumber,complaint.getText().trim(),appointment,doctor,list.getSelectionModel().getSelectedItem().toString());	
					
					}
	
				}
				else{
					
					//check if the patient or staff is available that day.
					if(DatabaseWork.CheckAvailabilityPatient(list.getSelectionModel().getSelectedItem().toString(), idNumber, appointment)){
						
						if(staffID != null && !staffID.isEmpty()){
							//staff should be available but this is to make sure.
							if(DatabaseWork.CheckAvailabilityStaff(list.getSelectionModel().getSelectedItem().toString(), staffID, appointment)){
							
								//set the appointment.
								safe = DatabaseWork.SetAppointmentPatient(idNumber,complaint.getText().trim(),appointment,staffID,list.getSelectionModel().getSelectedItem().toString());
							}
							//warnings for each if statement.
							else{
								warnAlert.setHeaderText("Warning!");
								warnAlert.setTitle("Appointment Status");
								warnAlert.setContentText("There was a problem. The Staff is seeing someone at this time.");
								warnAlert.showAndWait();
							}
						
						}
						else{
							warnAlert.setHeaderText("Warning!");
							warnAlert.setTitle("Staff Member Status");
							warnAlert.setContentText("There is no staff member by that name working here.");
							warnAlert.showAndWait();
						}

					}
				}
				if(safe){
					dataAlert.setHeaderText("Appointment Made Successfully");
					dataAlert.setTitle("Appointment Status\nDate: " + appointment + "\nTime: " + list.getSelectionModel().getSelectedItem().toString());
					dataAlert.setContentText("Appointment has been made, please remember when the appointment is and arrive 15 minutes before to ensure you are seen on time. Thank you for choosing us!");
					dataAlert.showAndWait();
					PatientInteractionWindow.WindowClose();
					
				}
				else{
					warnAlert.setHeaderText("Warning!");
					warnAlert.setTitle("Appointment Status");
					warnAlert.setContentText("There was an error. Please ensure you are not seeing someone else during this time.");
					warnAlert.showAndWait();
				}
			}
			
			
		});
	}
}






























