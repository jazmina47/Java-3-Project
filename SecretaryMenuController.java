package healthcareLook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class SecretaryMenuController implements Initializable{
	
	@FXML
	Button patient;
	@FXML
	Button appointment;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		patient.setOnAction(e->{
			
			HealthMainClass.startPatient();
		});
		
		appointment.setOnAction(f->{
			
		});
		
	}

}
