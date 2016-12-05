package healthcareLook;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ReceptionistMainMenuController implements Initializable {
	
	@FXML
	Button checkin;
	@FXML
	Button newpatient;
	@FXML
	Button cancelappoint;
	@FXML
	Button setappoint;
	@FXML
	Button paybill;
	@FXML
	Button signout;
	@FXML
	Label welcome;
	String patID;
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	Optional<ButtonType> result;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		checkin.setOnAction(e->{
			//retrieve id number first.
			patID = PatientFinderMain.startPatient();
		//	System.out.println("new window to check patients in");
			CheckinWindow.startCheckinScreen(patID);
		});
		newpatient.setOnAction(f->{
			HealthMainClass.startPatient();
		});
		cancelappoint.setOnAction(g->{
			//retrieve id number first.
			//add new window for canceling/no shows.
			patID = PatientFinderMain.startPatient();
		//	System.out.println("Did it get the id " + patID);
			CancelWindow.startCancelScreen(patID);
			
		});
		setappoint.setOnAction(h->{
			//Retrieve id number first then interact
			//Alert the user should the person have a pending bill
			patID = PatientFinderMain.startPatient();
			if(DatabaseWork.HasPendingBill(patID)){
				confirmAlert.setTitle("Pending Bill");
				confirmAlert.setHeaderText(patID + " has a pending bill");
				confirmAlert.setContentText("Do you wish to pay this bill?");
				ButtonType yes = new ButtonType("Yes");
				ButtonType no = new ButtonType("No");
				confirmAlert.getButtonTypes().setAll(yes, no);
				
				result = confirmAlert.showAndWait();
				if(result.get() == yes){
					PatientPaymentWindow.startCreatePaymentWindow(patID);
				}
				else{
					PatientInteractionWindow.startPInteraction(patID);
				}
				
			}
			else{
				PatientInteractionWindow.startPInteraction(patID);
			}
	
		});
		paybill.setOnAction(i->{
			//retrieve id number first. patient finder class will change to find id
			patID = PatientFinderMain.startPatient();
			PatientPaymentWindow.startCreatePaymentWindow(patID);
		});
		signout.setOnAction(j->{
			//close this window
			ReceptionistMainMenuWindow.stageClose();
		});
	}
	
	

}
