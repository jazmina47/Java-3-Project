package healthcareLook;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*The purpose of this program is to save and load patient data
 * this will use FileOutputStream and FileInputStream to bring and amend data.
 * I use SceneBuilder for the entire GUI.
 * 
 */

/*
 *NEEDS TO BE CLEANED!
 *
 */
public class HealthController implements Initializable{	
	//I create variables for labels, buttons, textfields, RadioButtons, and strings
	@FXML
	Label fNameLabel;
	@FXML
	Label lNameLabel;
	@FXML
	Label compainLabel;
	@FXML
	Label insuranceNameLabel;
	@FXML
	Label diagLabel;
	@FXML
	ImageView warningGender;
	@FXML
	ImageView warningfName;
	@FXML
	ImageView warninglName;
	@FXML
	ImageView warningSsn;
	@FXML
	ImageView warningAddress;
	@FXML
	ImageView warningCity;
	@FXML
	ImageView warningZip;
	@FXML
	ImageView warningCounty;
	@FXML
	ImageView warningPhone;
	@FXML
	ImageView warningDob;
	@FXML
	ImageView warningImmunizationStatus;
	@FXML
	ImageView warningEmergencyContact;
	@FXML
	ImageView warningRelationship;
	@FXML
	ImageView warningEmergencyContactNumber;
	@FXML
	ImageView warningInsuranceButton;
	@FXML
	ImageView warningInsuranceName;
	@FXML
	ImageView warningInsuranceNumber;
	@FXML
	ImageView warningPassword;
	@FXML
	Button butto;
	@FXML	
	Button quit;
	@FXML
	Button paymentButton;
	@FXML
	Button findSpecialist;
	@FXML
	Button clear;
	@FXML	
	Button find;
	@FXML
	TextField fNameText; 
	@FXML
	TextField lNameText;
	@FXML
	TextField complaint;
	@FXML
	TextField diagonsis;
	@FXML
	TextField ssText; 
	@FXML
	TextField addText;
	@FXML
	TextField cityText;
	@FXML
	TextField zipText;
	@FXML
	TextField countyText;
	@FXML
	TextField phoneText; 
	@FXML
	TextField immuText;
	@FXML
	TextField emergencyText;
	@FXML
	TextField relText;
	@FXML
	TextField emeConText;
	@FXML
	TextField medicaidText;
	@FXML
	TextField medicareText;
	@FXML
	TextField priInsuText;
	@FXML
	TextField insuName;
	@FXML
	TextField patientPassword;
	@FXML
	RadioButton genMale;
	@FXML
	RadioButton genFemale;
	@FXML
	RadioButton inAid;
	@FXML
	RadioButton inCare;
	@FXML
	RadioButton upToDate;
	@FXML
	RadioButton needUpdate;
	@FXML
	RadioButton otherGen;
	@FXML
	RadioButton inPriv;
	RadioButton genderTransfer;
	RadioButton insuranceTransfer;
	RadioButton immuTransfer;
	@FXML
	DatePicker dob;
	//ToggleGroup for the RadioButtons
	ToggleGroup gender = new ToggleGroup();
	ToggleGroup insurance = new ToggleGroup();
	ToggleGroup immuStatus = new ToggleGroup();
	
	
	//If there are no errors saveCheck will remain true and the data will be saved. but if there is one problem
	//The data will change saveCheck to false.
	boolean isAppointmentError=false;
	boolean isDobError=false;
	boolean saveCheck = true;
	
	
	Alert quitAlert = new Alert(AlertType.CONFIRMATION);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	Alert warnAlert = new Alert(AlertType.WARNING);
	Optional<ButtonType> result;
	

	
	//This is for the quit button, so you can get a warning and a confirm message should you wish to
	//quit the application.
	
	int quitClick;
	
	/*these are the HashMaps for storing patient information
	 * and for storing employee information.
	 */
	
	
	//The multiple strings i will need to store inputs for patient information.
	
	String fName,lName,comp,diag,patientData, addressString, ssString, cityString, zipString, countyString,
	phoneString, dobString, genderString, immuString, emcString, relationString,ecnString, insuranceButtonString,insuranceNameString, insuranceNumberString,
	appointString;
	

	//I create a tooltip.
	Tooltip errorTool;
	Patient newPatient;
	
	//id and idDouble is to generate a random id and make it integer

	//This is for setting the date to todays date.
	Date date = new Date();
	Calendar today = new GregorianCalendar();
	DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	LocalDate appointmentDate = null;
	//This will help convert the stored date of birth from String to LocalDate. 
	LocalDate dobDate = null;
	String todayDate;
	
	/*This is for splitting the insurance information back into 3 details,
	 * the carrier, the name, and the insurance number.
	 */
	
	String[] insuranceSplit;

		/* This check is for when all I want
		 * is letters and no numbers or symbols.
		 * Mostly used for names.
		 * The way this works is that we make a pattern listing everything that is not between and including A-Z and a-z
		 * This would exclude symbols and numbers
		 * Matcher puts the String that we send to it against the pattern in an attempt to find any matches, should it find
		 * one it will save the last match. the find method returns a boolean true if there was a match and false if there was not
		 * and I return the true or false.
		 */	
		public boolean getNonLetterCount(String s) {	
		     Pattern p = Pattern.compile("[^A-Za-z]");
		     Matcher m = p.matcher(s);
		     boolean b = m.find();
		     return b;
		 }
		//This check is for when I do not want letters or symbols. 
		public boolean getLetterCount(String s) {	
		     Pattern p = Pattern.compile("[^0-9]");
		     Matcher m = p.matcher(s);
		     boolean b = m.find();
		     return b;
		 }
		//This check is for when I do not want symbols.
		public boolean getSymbolCount(String s) {	
		     Pattern p = Pattern.compile("[^A-Za-z0-9]");
		     Matcher m = p.matcher(s);
		     boolean b = m.find();
		     return b;
		 }
		//This is for Address, which can have spaces.
		public boolean getAddressCount(String s) {	
		     Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
		     Matcher m = p.matcher(s);
		     boolean b = m.find();
		     return b;
		 }
	/* this check is for making sure
	 * the data received is just numbers.
	 * If the person adds a letter it will
	 * throw a NumberFormatException which returns true
	 * and if the length of the string is not 9
	 * the try returns true. If everything is ok
	 * then the try returns false.
	 */
	//Change The Names.
	public boolean ssnErrorCheck(String test3){
		try{
			int tester = Integer.parseInt(test3);	
			if(test3.length() == 9){
				return false;
			}
			else{
				return true;
			}
		}
		catch(NumberFormatException ex){
			return true;
		}
	}
	/* this check is for numbers with 10+ values like phone 
	 * numbers. Same as ssnErrorCheck.
	 */
	public boolean phoneErrorCheck(String test4){
		try{
			long tester = Long.parseLong(test4);
			if(test4.length() == 10){
				return false;
			}
			else{
				return true;
			}
		}
		catch(NumberFormatException ex){
			return true;
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		

		todayDate = format.format(date);
		
		/* If the enter button is pressed then the program
		 * will confirm that not only all the textfields have
		 * information inside but that the first and last
		 * names do not have numbers and if either or happens 
		 * the error message will pop to alert the user.
		 * If the information is ok then the error text is reset
		 * and you will see nothing.
		 */
		butto.setOnAction(e ->{

			
			/*This code allows for the error test to be "reset" each time
			 * you click the save information button.
			 */

			saveCheck = true;
			
			if(fNameText.getText().trim().equals("")){
				warningfName.setVisible(true);
				errorTool.install(warningfName, new Tooltip("Please enter a first name."));
				
				//This is in case the user wants to save the data
				//I want to remind them again that the data could be loss so
				//I reset the count to zero so it can start at 1 next time they click close
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getNonLetterCount(fNameText.getText().trim().toString())){
					warningfName.setVisible(true);
					errorTool.install(warningfName, new Tooltip("No numbers or symbols."));
						quitClick =0;
						saveCheck = false;
						
				}
				else{
					warningfName.setVisible(false);
				}
				
			if(lNameText.getText().trim().equals("")){
				warninglName.setVisible(true);
				errorTool.install(warninglName, new Tooltip("Please enter the last name."));
					quitClick =0;
					saveCheck = false;
					
				}
				else if(getNonLetterCount(lNameText.getText().toString())){
					warninglName.setVisible(true);
					errorTool.install(warninglName, new Tooltip("No numbers or symbols."));
								quitClick =0;
								saveCheck = false;
								
					}
				else{
					warninglName.setVisible(false);
				}
			if(ssText.getText().trim().equals("")){
				warningSsn.setVisible(true);
				errorTool.install(warningSsn, new Tooltip("Please enter SSN"));
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getSymbolCount(ssText.getText().trim().toString())){
					warningSsn.setVisible(true);
					errorTool.install(warningSsn, new Tooltip("No symbols."));
						quitClick =0;
						saveCheck = false;
				}
			
				else if(ssnErrorCheck(ssText.getText().trim().toString())){
					if (ssText.getText().trim().toString().length() != 9){
						warningSsn.setVisible(true);
						errorTool.install(warningSsn, new Tooltip("Must be 9 numbers(only) long."));
						quitClick =0;
						saveCheck = false;
					}
					else{
						warningSsn.setVisible(true);
						errorTool.install(warningSsn, new Tooltip("No letters."));
					}
					quitClick =0;
					saveCheck = false;
				}
				else{
					warningSsn.setVisible(false);
				}
			if(addText.getText().trim().equals("")){
				warningAddress.setVisible(true);
				errorTool.install(warningAddress, new Tooltip("Please enter address."));
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getAddressCount(addText.getText().trim().toString())){
					warningAddress.setVisible(true);
					errorTool.install(warningAddress, new Tooltip("No symbols."));
						quitClick =0;
						saveCheck = false;
				}
				else{
					warningAddress.setVisible(false);
				}
			
			if(cityText.getText().trim().equals("")){
				warningCity.setVisible(true);
				errorTool.install(warningCity, new Tooltip("Please enter city."));
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(cityText.getText().toString())){
				warningCity.setVisible(true);
				errorTool.install(warningCity, new Tooltip("No numbers or symbols."));
							quitClick =0;
							saveCheck = false;
							
				}
			else{
				warningCity.setVisible(false);
			}
			if(zipText.getText().trim().equals("")){
				warningZip.setVisible(true);
				errorTool.install(warningZip, new Tooltip("Please enter zip code."));
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getSymbolCount(zipText.getText().toString())){
				warningZip.setVisible(true);
				errorTool.install(warningZip, new Tooltip("No symbols."));
							quitClick =0;
							saveCheck = false;
							
				}
			else{
				warningZip.setVisible(false);
			}
			if(countyText.getText().trim().equals("")){
				warningCounty.setVisible(true);
				errorTool.install(warningCounty, new Tooltip("Please enter county."));
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(countyText.getText().toString())){
				warningCounty.setVisible(true);
				errorTool.install(warningCounty, new Tooltip("No numbers or symbols."));
							quitClick =0;
							saveCheck = false;
							
				}
			else{
				warningCounty.setVisible(false);
			}
			if(phoneText.getText().trim().equals("")){
				warningPhone.setVisible(true);
				errorTool.install(warningPhone, new Tooltip("Please enter a phone number"));
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getSymbolCount(phoneText.getText().trim().toString())){
					warningPhone.setVisible(true);
					errorTool.install(warningPhone, new Tooltip("No symbols."));
						quitClick =0;
						saveCheck = false;
				}
			
				else if(phoneErrorCheck(phoneText.getText().trim().toString())){
					warningPhone.setVisible(true);
					if (phoneText.getText().trim().toString().length() != 10){
						errorTool.install(warningPhone, new Tooltip("10 numbers(only)."));
						quitClick =0;
						saveCheck = false;
					}
					else{
						warningPhone.setVisible(true);
						errorTool.install(warningPhone, new Tooltip("No letters."));
					}
					quitClick =0;
					saveCheck = false;
				}
				else{
					warningPhone.setVisible(false);
				}
			isDobError=false;
				try{
					if(dob.getValue().equals("")){
					}
				}
				catch(NullPointerException ex){
					warningDob.setVisible(true);
					errorTool.install(warningDob, new Tooltip("Please enter date of birth."));
					quitClick =0;
					saveCheck = false;
					isDobError= true;
				}
			if(!isDobError){
				warningDob.setVisible(false);
			}
			if(!(genMale.isSelected()||genFemale.isSelected()||otherGen.isSelected())){
				warningGender.setVisible(true);
				errorTool.install(warningGender, new Tooltip("Please pick a gender."));
				quitClick =0;
				saveCheck = false;
			}
			else{
				warningGender.setVisible(false);
			}
			if(!(needUpdate.isSelected()||upToDate.isSelected())){
				warningImmunizationStatus.setVisible(true);
				errorTool.install(warningImmunizationStatus, new Tooltip("Please Select Immuzation Status"));
				quitClick =0;
				saveCheck = false;
			}
			else{
				warningImmunizationStatus.setVisible(false);
			}
			if(emergencyText.getText().trim().equals("")){
				warningEmergencyContact.setVisible(true);
				errorTool.install(warningEmergencyContact, new Tooltip("Need a emergency contact."));
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(emergencyText.getText().toString())){
				warningEmergencyContact.setVisible(true);
				errorTool.install(warningEmergencyContact, new Tooltip("No numbers or symbols."));
							quitClick =0;
							saveCheck = false;
			}
			else{
				warningEmergencyContact.setVisible(false);
			}
			if(relText.getText().trim().equals("")){
				warningRelationship.setVisible(true);
				errorTool.install(warningRelationship, new Tooltip("Please enter relationship to person."));
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(relText.getText().toString())){
				warningRelationship.setVisible(true);
				errorTool.install(warningRelationship, new Tooltip("No numbers or symbols."));
							quitClick =0;
							saveCheck = false;
			}
			else{
				warningRelationship.setVisible(false);
			}
			if(emeConText.getText().trim().equals("")){
				warningEmergencyContactNumber.setVisible(true);
				errorTool.install(warningEmergencyContactNumber, new Tooltip("Please enter contact number."));
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getSymbolCount(emeConText.getText().toString())){
				warningEmergencyContactNumber.setVisible(true);
				errorTool.install(warningEmergencyContactNumber, new Tooltip("No symbols."));
							quitClick =0;
							saveCheck = false;
			}
			else if(phoneErrorCheck(emeConText.getText().trim().toString())){
				if (emeConText.getText().trim().toString().length() != 10){
					warningEmergencyContactNumber.setVisible(true);
					errorTool.install(warningEmergencyContactNumber, new Tooltip("Must be 10 numbers(only) long."));
					quitClick =0;
					saveCheck = false;
				}
				else{
					warningEmergencyContactNumber.setVisible(true);
					errorTool.install(warningEmergencyContactNumber, new Tooltip("No letters."));
				}
				quitClick =0;
				saveCheck = false;
			}
			else{
				warningEmergencyContactNumber.setVisible(false);
			}
			if(!(inAid.isSelected()||inCare.isSelected()||inPriv.isSelected())){
				warningInsuranceButton.setVisible(true);
				errorTool.install(warningInsuranceButton, new Tooltip("Please pick your insurance."));
				quitClick =0;
				saveCheck = false;
			}
			else if(inPriv.isSelected() && insuName.getText().trim().equals("")){
				warningInsuranceButton.setVisible(false);
				warningInsuranceName.setVisible(true);
				errorTool.install(warningInsuranceName, new Tooltip("Please enter Insurance name."));
				quitClick =0;
				saveCheck = false;
			}
			else if(medicareText.getText().trim().equals("")){
				warningInsuranceButton.setVisible(false);
				warningInsuranceNumber.setVisible(true);
				errorTool.install(warningInsuranceNumber, new Tooltip("Please enter Insurance Number."));
				quitClick =0;
				saveCheck = false;
			}
			else{
				warningInsuranceNumber.setVisible(false);
				warningInsuranceButton.setVisible(false);
			}
			
			/* I save all the data into strings to better place them into 
			 * a file for saving later.
			 */
				if (saveCheck){
					
					if(genMale.isSelected()){
						genderString = "Male";
					}
					else if (genFemale.isSelected()){
						genderString = "Female";
					}
					else{
						genderString = "Other";
					}
					if(upToDate.isSelected()){
						immuString = "Up to date";
					}
					else{
						immuString = "Expired";
					}
					fName = fNameText.getText().toString().trim();
					lName = lNameText.getText().toString().trim();
					ssString = ssText.getText().toString().trim();
					addressString = addText.getText().toString().trim();
					cityString = cityText.getText().toString().trim();
					zipString = zipText.getText().toString().trim();
					countyString = countyText.getText().toString().trim();
					phoneString = phoneText.getText().toString().trim();
					dobString = dob.getValue().toString().trim();	
					emcString = emergencyText.getText().toString().trim();
					relationString = relText.getText().toString().trim();
					ecnString = emeConText.getText().toString().trim();
					if(inPriv.isSelected()){
						insuranceButtonString = inPriv.getText().toString();
						insuranceNameString = insuName.getText().toString();
					}
					else{
						if(inAid.isSelected()){
							insuranceButtonString = inAid.getText().toString();
							insuranceNameString = "Medicaid ";
						}
						else if(inCare.isSelected()){
							
							insuranceButtonString = inCare.getText().toString();
							insuranceNameString = "Medicare ";
						}
						insuranceNameString = "";
					}
					insuranceNumberString = medicareText.getText().toString();
					
					
					newPatient = new Patient(fName,lName,ssString,addressString,cityString,zipString,countyString,phoneString,
							dobString,genderString,immuString,emcString,relationString,ecnString,(insuranceButtonString +" " + insuranceNameString + " " +insuranceNumberString));														
					
				//This will save the patient and check if it has been saved successfully.
					
					boolean isSaved = DatabaseWork.SavePatient(newPatient);
					if(isSaved){
						
						String idNumber = DatabaseWork.RetrieveIDPatient(newPatient.getSsn());
						dataAlert.setTitle("Data Successfully Saved!");
						dataAlert.setHeaderText(idNumber);
						dataAlert.setContentText("Please write down and remember your id number.");
						dataAlert.showAndWait();
						
						PatientInteractionWindow.startPInteraction(idNumber);
						HealthMainClass.stageClose();
						
					}
					else{
						
						warnAlert.setTitle("Error in saving data");
						warnAlert.setHeaderText("Warning!");
						warnAlert.setContentText("If you already have an account with us please log in");
						warnAlert.showAndWait();
						//Message box about error in saving data.
					}
					
				}			
		});
		/* If the person clicks the find button they will open another window made
		 * for finding a patient
		 */

		/* IF the person clicks the close button the program will close. Any data
		 * not saved will be lost.
		 */
		quit.setOnAction(e->{
			//Add confirmation box
			quitAlert.setTitle("Please Wait");
			quitAlert.setHeaderText("Confirmation");
			quitAlert.setContentText("Are you sure you want to quit?");
			
			
			result = quitAlert.showAndWait();
			
			if(result.get() == ButtonType.OK){
				
				System.exit(-1);
			}
			else{
				quitAlert.close();
			}

		});
		/*This clears all the text in the fields.
		 *Should be completely empty.
		 */	
		clear.setOnAction(e->{	
			fNameText.clear();
			lNameText.clear();
			ssText.clear();
			addText.clear();
			cityText.clear();
			zipText.clear();
			phoneText.clear();
			dob.setValue(null);
			genMale.setSelected(false);
			genFemale.setSelected(false);
			otherGen.setSelected(false);
			upToDate.setSelected(false);
			needUpdate.setSelected(false);
			emeConText.clear();
			relText.clear();
			emergencyText.clear();
			inAid.setSelected(false);
			inCare.setSelected(false);
			inPriv.setSelected(false);
			insuName.clear();
			medicareText.clear();	
		});	
		
	}

}
