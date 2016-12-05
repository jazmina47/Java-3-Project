package healthcareLook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
/*
 * The purpose of this window is to check if the person is a doctor/admin (staff)
 * or a receptionist.
 */
public class MainMenuController implements Initializable {

	//here are all the buttons used in this window
	@FXML
	Button buttonStaff;
	@FXML
	Button buttonPatient;
	@FXML
	Button buttonQuit;
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				//Each button will go to a different login screen or close the program.
				//This one goes to the doctor/admin login
				buttonStaff.setOnAction(e->{
					HealthLoginStaff.startLoginScreen();
				});
				//This one goes to the receptionist login for patients.
				buttonPatient.setOnAction(e ->{
					ReceptionistLoginWindow.startLoginScreen();
				});
				//this will close the program.
				buttonQuit.setOnAction(e ->{
					MainMenu.stageClose();
				});
		
	}
}
