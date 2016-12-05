package healthcareLook;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
/*
 * The purpose of this window is to log the receptionist in.
 */
public class ReceptionistLoginWindowController implements Initializable{
	
	//the nodes I use.
	
	@FXML
	Button buttonSubmit;
	@FXML
	TextField staffId;
	@FXML
	ImageView errorImage;
	Tooltip errortip;
	Alert testAlert = new Alert(AlertType.INFORMATION);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		//This button will check if the data is correct or not
		buttonSubmit.setOnAction(e->{
			//Data about retrieving and checking data.
			//If staffID (textfield) is empty or holds wrong characters it will throw an image with a tooltip.
			if(staffId.getText().trim().equals("")){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}
			else if(getLetterCount(staffId.getText().trim())){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}
			//if everything is OK then the id is checked with the database to see
			// if the person is in the database or not.
			else if(DatabaseWork.IDCheckStaff(staffId.getText().trim())){
				
				testAlert.setAlertType(AlertType.INFORMATION);
				testAlert.setTitle("Staff Login Result");
				testAlert.setHeaderText("");
				testAlert.setContentText("You Successfully logged in!");
				testAlert.showAndWait();
				
				//This takes you to the Patient Finder window.
				//We consider all patients as existing patients unless they
				//say they are new
				
				//PatientFinderMain.startPatient();
				ReceptionistMainMenuWindow.startReceptionstMenu();
				ReceptionistLoginWindow.stageClose();
				
		
			}
			//if the id number is not found this will give us a messagebox
			//which will alert the user that it was not found.
			else{
				testAlert.setAlertType(AlertType.WARNING);
				testAlert.setTitle("Staff Login Result");
				testAlert.setHeaderText("Problem!");
				testAlert.setContentText("ID NUMBER NOT FOUND");
				testAlert.showAndWait();
			}
		});
	}
	
	
	
	
	
	//This check is for when I do not want letters or symbols. 
	public boolean getLetterCount(String s) {	
	     Pattern p = Pattern.compile("[^0-9]");
	     Matcher m = p.matcher(s);
	     boolean b = m.find();
	     return b;
	 }

}






