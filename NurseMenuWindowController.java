package healthcareLook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class NurseMenuWindowController implements Initializable{

	@FXML
	Button receptButton;
	@FXML
	Button notes;
	@FXML
	Button noShow;
	@FXML
	Button signout;
	String staffID;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		staffID = NurseMenuWindow.getID();
		
		receptButton.setOnAction(e->{
			ReceptionistMainMenuWindow.startReceptionstMenu();
		});
		
		notes.setOnAction(f->{
			NurseNotesWindow.startNotesWindow(staffID);
			//Window for taking notes of the patient
			
		});
		
		noShow.setOnAction(g->{
			//Window for in case the patient leaves between checking in and the nurse seeing the patient.
			NurseCancelWindow.startNurseCancelWindow(staffID);
			
		});
		signout.setOnAction(h->{
			NurseMenuWindow.stageClose();
		});
	}

}
