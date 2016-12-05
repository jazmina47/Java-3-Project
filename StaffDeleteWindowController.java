package healthcareLook;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
/*
 * The purpose of this window is to delete the staff member. This will delete
 * them from the database but the id number will still be used.
 * This is so the patient appointment information stays in case they wish to
 * know when they came to the clinic.
 */
public class StaffDeleteWindowController implements Initializable {
	
	
	@FXML
	Button submit;
	@FXML
	TextField staffId;
	@FXML
	ImageView errorImage;
	Tooltip errortip;
	Alert dataAlert = new Alert(AlertType.CONFIRMATION);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	Alert warnAlert = new Alert(AlertType.WARNING);
	Employee employeeHolder;
	
	Optional<ButtonType> result;
	Optional<ButtonType> result2;
	String adminId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		submit.setOnAction(e->{
			//I set the image as invisible because I assume the person will enter the information correctly.
			errorImage.setVisible(false);
			//If the information is blank or empty it will set the error image to visible and the database
			//is not accessed.
			if(staffId.getText().trim().equals("")){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}
			else if(getLetterCount(staffId.getText().trim())){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}//This checks if the employee is in the database.
			else if(DatabaseWork.IDCheckStaff(staffId.getText().trim())){
				//this will retrieve the information of the employee.
				employeeHolder = DatabaseWork.IDStaffConfirmation(staffId.getText().trim());
				
				dataAlert.setTitle("Confirmation");
				dataAlert.setHeaderText("Are you sure you wish to delete this employee?");
				//This displays the information of the employee. This is to make sure the admin knows who 
				//he is about to delete (fire).
				dataAlert.setContentText("Name: " + employeeHolder.getfName() + " " + employeeHolder.getlName() + 
						"\nStaff Postion: " + employeeHolder.getPosition() + "\nSocial Security Number: " + employeeHolder.getSsn());
				result = dataAlert.showAndWait();
				
				if(result.get() == ButtonType.OK){
					dataAlert.close();
					//if the person wishes to delete the staff then they are removed.
					DatabaseWork.RemoveStaff(staffId.getText().trim());
					
					confirmAlert.setTitle("Successful Deletion");
					confirmAlert.setHeaderText("The Deletion of the employee was successful.");
					confirmAlert.setContentText("Do you wish to delete another employee?");
					
					ButtonType yes = new ButtonType("YES");
					ButtonType no = new ButtonType("NO");
					confirmAlert.getButtonTypes().setAll(yes, no);
					result2 = confirmAlert.showAndWait();
					//if the person has another employee to delete then the messages box closes and they can
					//enter in another id.
					if(result2.get() == yes){
						confirmAlert.close();
					}
					else{
						//else they will be moved to the diagnosis window if they
						//have a diagnosis to put, or moved to the interaction window
						adminId = StaffDeleteWindow.getId();
						StaffDeleteWindow.stageClose();
					}
				}
				else{
					staffId.clear();
					dataAlert.close();
				}

			}
			
			else{
				warnAlert.setTitle("An Error Has Occurred");
				warnAlert.setHeaderText("Staff ID not found.");
				warnAlert.setContentText("Please Make sure you have the correct Staff ID");
				warnAlert.showAndWait();
			}
			
		});
		//If the admin has a change of heart and does not want to delete they can hit the skip
		//button and this will take them to the next window.
		
	}
	
	
	
	//This check is for when I do not want letters or symbols. 
		public boolean getLetterCount(String s) {	
		     Pattern p = Pattern.compile("[^0-9]");
		     Matcher m = p.matcher(s);
		     boolean b = m.find();
		     return b;
		 }
	

}







