package healthcareLook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LostIdWindowController implements Initializable{
	
	@FXML
	TextField fName;
	@FXML
	TextField lName;
	@FXML
	TextField ssn;
	@FXML
	Button confirm;
	String patId;
	
	Alert dataAlert= new Alert(AlertType.INFORMATION);
	Alert warnAlert= new Alert(AlertType.WARNING);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		confirm.setOnAction(e->{
			if(fName.getText().trim().isEmpty() || lName.getText().trim().isEmpty() || ssn.getText().trim().isEmpty()){
				warnAlert.setTitle("Warning!");
				warnAlert.setHeaderText("Please fill in all fields.");
				warnAlert.showAndWait();
			}
			else{
				if(DatabaseWork.PersonExists(fName.getText().trim(), lName.getText().trim(), ssn.getText().trim())){
					patId = DatabaseWork.RetrieveIDPatient(ssn.getText().trim());
					
					dataAlert.setTitle("ID retrieved!");
					dataAlert.setHeaderText("Please write the ID number down \nID#: " + patId);
					dataAlert.showAndWait();
				}
				else{
					warnAlert.setTitle("Warning!");
					warnAlert.setHeaderText("A person with that information does not exist.");
					warnAlert.showAndWait();
				}
			}
		});
		
	}

}
