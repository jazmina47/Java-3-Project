package healthcareLook;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
/*
 * The purpose of this window is to allow the doctors or admins to log in.
 */
public class HealthStaffLoginController implements Initializable{

	
	
	@FXML
	Button buttonSubmit;
	@FXML
	TextField staffId;
	@FXML
	TextField staffPassword;
	@FXML
	ImageView errorImage;
	Tooltip errortip;
	Alert testAlert;
	Alert testAlert2 = new Alert(AlertType.INFORMATION);
	Alert testAlert3 = new Alert(AlertType.INFORMATION);
	
	Optional<ButtonType> result;
	
	
	//This check is for when I do not want letters or symbols. 
	public boolean getLetterCount(String s) {	
	     Pattern p = Pattern.compile("[^0-9]");
	     Matcher m = p.matcher(s);
	     boolean b = m.find();
	     return b;
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
testAlert= new Alert(AlertType.INFORMATION);
		
		buttonSubmit.setOnAction(e->{
			//this checks if the textfield was empty or if something wrong was entered.
			
			if(staffId.getText().trim().equals("")){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}
			else if(getLetterCount(staffId.getText().trim())){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}
			//this will check if the staff id exists in the database.
			else if(DatabaseWork.IDCheckStaff(staffId.getText().trim())){
				
				String iddemo = DatabaseWork.RetrieveIDStaff("996582227");
				
			//	System.out.println(iddemo);
				
				
				// if staff member has appointments open diagnosis window.
				// an If statement on top to check if a person is admin, if they are it sends them to
				// an admin window.
				
				//if the person is a receptionist this will tell them to log in from the receptionist window.
				if(DatabaseWork.CheckForStaffPosition(staffId.getText().trim()).equals("Receptionist")){
					testAlert.setAlertType(AlertType.WARNING);
					testAlert.setTitle("Staff Login Result");
					testAlert.setHeaderText("You are a receptionist.");
					testAlert.setContentText("Please log in from the receptionist window to set an appointment");
					testAlert.showAndWait();
					//This is to return the person back to the previous window.
					HealthLoginStaff.stageClose();
				}
				else if(DatabaseWork.CheckForStaffPosition(staffId.getText().trim()).equals("Nurse")){
					testAlert2.setAlertType(AlertType.INFORMATION);
					testAlert2.setTitle("Staff Login Result");
					testAlert2.setHeaderText("");
					testAlert2.setContentText("You Successfully logged in!");
					testAlert2.showAndWait();
					NurseMenuWindow.startNurseWindow(staffId.getText().trim());
				}
				//if the person is an admin it ask if they wish to add an employee or delete an employee.
				else if(DatabaseWork.CheckForStaffPosition(staffId.getText().trim()).equals("Admin")){
					testAlert2.setAlertType(AlertType.INFORMATION);
					testAlert2.setTitle("Staff Login Result");
					testAlert2.setHeaderText("");
					testAlert2.setContentText("You Successfully logged in!");
					testAlert2.showAndWait();
					AdminMenuWindow.startAdminWindow(staffId.getText().trim());
						
					}
				else{
					testAlert2.setAlertType(AlertType.INFORMATION);
					testAlert2.setTitle("Staff Login Result");
					testAlert2.setHeaderText("");
					testAlert2.setContentText("You Successfully logged in!");
					testAlert2.showAndWait();
					//if the person has appointments to add diagnosis to this will send them to the
					//diagnosis window.
					DoctorMenuWindow.startDoctorWindow(staffId.getText().trim());
				}
			}
			else{
				//the id number was not inside the database.
				testAlert3.setAlertType(AlertType.WARNING);
				testAlert3.setTitle("Staff Login Result");
				testAlert3.setHeaderText("Problem!");
				testAlert3.setContentText("ID NUMBER NOT FOUND");
				testAlert3.showAndWait();
			}
			
		});
		
		
	}
}
