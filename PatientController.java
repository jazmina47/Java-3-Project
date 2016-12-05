package healthcareLook;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/*
 * NO PROBLEMS...
 * The purpose of this window is to find a patient or go to the next window
 * which will make a new patient.
 */
public class PatientController implements Initializable{
	
	
	@FXML
	TextField iDFinder;
	@FXML
	Button newPatient;
	@FXML
	Label ssConfirmation;
	@FXML
	Label lNameConfirmation;
	@FXML
	Label addConfirmation;
	@FXML
	Label fNameConfirmation;
	@FXML
	Label confirmOrError;
	@FXML
	Button submitPatient;
	Optional<ButtonType> result;
	@FXML
	ImageView errorImage;
	Alert testAlert = new Alert(AlertType.CONFIRMATION);
	Alert problemAlert = new Alert(AlertType.WARNING);
	

	Tooltip errortip;
	int patientConfirm;
	int quitClick;
	int patientid;
	
	Patient patientHolder;
	
	
	
	//This check is for when I do not want letters or symbols. 
	public boolean getLetterCount(String s) {	
	     Pattern p = Pattern.compile("[^0-9]");
	     Matcher m = p.matcher(s);
	     boolean b = m.find();
	     return b;
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*This button is for  confirming if the information gathered is correct or not. 
		 * If it is correct then the data will be inserted into another window.
		 */
		submitPatient.setOnAction(e->{
			
			if(iDFinder.getText().trim().equals("")){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your patient ID"));
			}
			else if(getLetterCount(iDFinder.getText().trim())){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your patient ID"));
			}
			else if(DatabaseWork.IDCheckPatient(iDFinder.getText().trim())){
			//	System.out.println("Data retrieved!");
				testAlert.setTitle("Patient Login Result");
				testAlert.setHeaderText("Is this your information?");
				patientHolder = (DatabaseWork.IDPatientConfirmation(iDFinder.getText().trim()));
				testAlert.setContentText("First Name: " + patientHolder.getfName()  + "\nLast Name: " + patientHolder.getlName() + "\nSocial Security Number: " + patientHolder.getSsn());
				
				
				result = testAlert.showAndWait();
				
				if(result.get() == ButtonType.OK){

						PatientFinderMain.setID(iDFinder.getText().trim());
						PatientFinderMain.stageClose();
			
				}
	
				}
			//IF the id number was not found in the database.
			else{
				problemAlert.setTitle("Patient Login Result");
				problemAlert.setHeaderText("Warning!");
				problemAlert.setContentText("ID NUMBER NOT FOUND");
				problemAlert.showAndWait();
			}
			
			
			
			
		});
		
		
	}

	
}

















