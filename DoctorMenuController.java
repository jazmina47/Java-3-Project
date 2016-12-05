package healthcareLook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class DoctorMenuController implements Initializable{
	
	@FXML
	Button receptionist;
	@FXML
	Button nurse;
	@FXML
	Button diagnosis;
	@FXML
	Button noShow;
	@FXML
	Button signout;
	String staffID;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		staffID = DoctorMenuWindow.getID();
		receptionist.setOnAction(a->{
			ReceptionistMainMenuWindow.startReceptionstMenu();
		});
		nurse.setOnAction(b->{
			NurseMenuWindow.startNurseWindow(staffID);
		});
		diagnosis.setOnAction(c->{
			StaffDiagonsisWindow.startCreateDiagonsisWindow(staffID);
		});
		noShow.setOnAction(d->{
			DoctorCancelWindow.startDoctorCancelWindow(staffID);
		});
		signout.setOnAction(e->{
			DoctorMenuWindow.stageClose();
		});
		
	}

	
}
