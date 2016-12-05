package healthcareLook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AdminMenuWindowController implements Initializable{

	
	@FXML
	Button receptionist;
	@FXML
	Button nurse;
	@FXML
	Button removeEmployee;
	@FXML
	Button addEmployee;
	@FXML
	Button doctor;
	@FXML
	Button signout;
	String staffID;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		staffID = AdminMenuWindow.getID();
		receptionist.setOnAction(a->{
			ReceptionistMainMenuWindow.startReceptionstMenu();
		});
		nurse.setOnAction(b->{
			NurseMenuWindow.startNurseWindow(staffID);
		});
		doctor.setOnAction(c->{
			DoctorMenuWindow.startDoctorWindow(staffID);
		});
		addEmployee.setOnAction(d->{
			HealthStaffCreate.startCreateWindow(staffID);
		});
		removeEmployee.setOnAction(f->{
			StaffDeleteWindow.startDeletionWindow(staffID);
		});
		signout.setOnAction(e->{
			AdminMenuWindow.stageClose();
		});
	}
		
}
