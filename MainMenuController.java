package healthcareLook;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

	@FXML
	Button buttonStaff;
	@FXML
	Button buttonPatient;
	@FXML
	Button buttonQuit;
	
	
	@FXML
	public void Intialize(){
		
		
		buttonPatient.setOnAction(e ->{
			HealthMainClass.startPatient();
		});
		
		buttonQuit.setOnAction(e ->{
			MainMenu.stageClose();
		});
	}
}
