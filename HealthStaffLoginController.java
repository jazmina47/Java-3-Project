package healthcareLook;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class HealthStaffLoginController {

	
	
	@FXML
	Button buttonSubmit;
	@FXML
	Button buttonCreateNewStaff;
	@FXML
	TextField staffId;
	@FXML
	TextField staffPassword;
	Alert testAlert;
	@FXML
	public void Intialize(){
		testAlert= new Alert(AlertType.INFORMATION);
		
		buttonSubmit.setOnAction(e->{
			//Data about retrieving and checking data.
			System.out.println("Data retrieved!");
			testAlert.setTitle("Staff Login Result");
			testAlert.setContentText("You Successfully logged in!");
			testAlert.showAndWait();
			
		});
		
		//Carries you to the next window which will create staff.
		buttonCreateNewStaff.setOnAction(e ->{
			HealthStaffCreate.startCreateWindow();
		});
		
	}
}
