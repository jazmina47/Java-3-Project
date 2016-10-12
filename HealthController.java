package healthcareLook;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/*The purpose of this program is to save and load patient data
 * this will use FileOutputStream and FileInputStream to bring and amend data.
 * I use SceneBuilder for the entire GUI.
 * 
 */

/*TEST SAVING AND LOADING OF DATA!
 *
 */

public class HealthController {
	
	//I create variables for labels, buttons, textfields, RadioButtons, and strings
	
	@FXML
	Label fNameLabel;
	@FXML
	Label lNameLabel;
	@FXML
	Label compainLabel;
	@FXML
	Label diagLabel;
	@FXML
	Label saveResult;
	@FXML
	Label fError;
	@FXML
	Label lError;
	@FXML
	Label cError;
	@FXML
	Label ssError;
	@FXML
	Label addError;
	@FXML
	Label cityError;
	@FXML
	Label zipError;
	@FXML
	Label countyError;
	@FXML
	Label phoneError;
	@FXML
	Label dobError;
	@FXML
	Label immError;
	@FXML
	Label emeError;
	@FXML
	Label relError;
	@FXML
	Label ecnError;
	@FXML
	Label aidError;
	@FXML
	Label careError;
	@FXML
	Label privError;
	@FXML
	Label appError;
	@FXML
	Label genError;
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
	@FXML
	DatePicker appointment;
	//ToggleGroup for the RadioButtons
	ToggleGroup gender = new ToggleGroup();
	ToggleGroup insurance = new ToggleGroup();
	ToggleGroup immuStatus = new ToggleGroup();
	
	//a Label for the clock
	@FXML
	Label testClock;
	
	//If there are no errors saveCheck will remain true and the data will be saved. but if there is one problem
	//The data will change saveCheck to false.
	
	boolean saveCheck = true;
	String errorDetails;
	
	//This is to check if the data was retrieved from a file or not, so you do not give the person a new ID.
	//This will also override the past data stored.
	boolean isNotRetrieved = true;
	
	//This is for the quit button, so you can get a warning and a confirm message should you wish to
	//quit the application.
	
	int quitClick;
	
	
	/*these are the HashMaps for storing patient information
	 * and for storing employee information.
	 */
	
	
	HashMap<Integer,Patient> pList = new HashMap<Integer,Patient>();
	HashMap<Integer,Employee> sList = new HashMap<Integer,Employee>();
	
	//The multiple strings i will need to store inputs for patient information.
	
	String fName,lName,comp,diag,patientData, addressString, ssString, cityString, zipString, countyString,
	phoneString, dobString, genderString, immuString, emcString, relationString,ecnString, insuranceButtonString,insuranceNameString, insuranceNumberString,
	appointString;
	
	//id and idDouble is to generate a random id and make it integer
	int id;
	Double idDouble;
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


	@FXML
	public void Intialize(){

		
		genMale.setToggleGroup(gender);
		genFemale.setToggleGroup(gender);
		inPriv.setToggleGroup(insurance);
		inAid.setToggleGroup(insurance);
		inCare.setToggleGroup(insurance);
		otherGen.setToggleGroup(gender);
		needUpdate.setToggleGroup(immuStatus);
		upToDate.setToggleGroup(immuStatus);
		
		testClock = new RealClock();
		
		todayDate = format.format(date);
		
		

		/* this is for making sure the save to the file is correct*/
		//I wrap the text around so it does not continue in one line and go on as "..."
		saveResult.setWrapText(true);
		careError.setWrapText(true);
		ecnError.setWrapText(true);
		immError.setWrapText(true);
		saveResult.setFont(Font.font(null,FontWeight.BOLD, 18));
		
		
		
		/* If the enter button is pressed then the program
		 * will confirm that not only all the textfields have
		 * information inside but that the first and last
		 * names do not have numbers and if either or happens 
		 * the error message will pop to alert the user.
		 * If the information is ok then the error text is reset
		 * and you will see nothing.
		 */
		butto.setOnAction(e ->{


			try{
				FileInputStream fileinput = new FileInputStream("patient.txt");
				ObjectInputStream file2input= new ObjectInputStream(fileinput);
				try{
					if(pList.isEmpty()){
						
					pList = (HashMap) file2input.readObject();
					
					}
				
				}
				catch(EOFException ex1){
					
				}

				
				file2input.close();
			}
			catch(Exception ex){
				System.out.println("Gotta make new file!");
			}
			
			/*This code allows for the error test to be "reset" each time
			 * you click the save information button.
			 */
		
			fError.setText("");
			lError.setText("");
			cError.setText("");
			saveResult.setText("");
			ssError.setText("");
			addError.setText("");
			cityError.setText("");
			zipError.setText("");
			countyError.setText("");
			phoneError.setText("");
			dobError.setText("");
			genError.setText("");
			immError.setText("");
			emeError.setText("");
			relError.setText("");
			ecnError.setText("");
			careError.setText("");
			appError.setText("");
			saveCheck = true;
			
			if(fNameText.getText().trim().equals("")){
				fError.setText("Please enter the first name.");
				//This is in case the user wants to save the data
				//I want to remind them again that the data could be loss so
				//I reset the count to zero so it can start at 1 next time they click close
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getNonLetterCount(fNameText.getText().trim().toString())){
						fError.setText("No numbers or symbols.");
						quitClick =0;
						saveCheck = false;
						
						
				}
				
			if(lNameText.getText().trim().equals("")){
					lError.setText("Please enter the last name.");
					quitClick =0;
					saveCheck = false;
					
				}
				else if(getNonLetterCount(lNameText.getText().toString())){
								lError.setText("No numbers or symbols.");
								quitClick =0;
								saveCheck = false;
								
					}
			if(ssText.getText().trim().equals("")){
				ssError.setText("Please enter SSN");
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getSymbolCount(ssText.getText().trim().toString())){
						ssError.setText("No symbols.");
						quitClick =0;
						saveCheck = false;
				}
			
				else if(ssnErrorCheck(ssText.getText().trim().toString())){
					if (ssText.getText().trim().toString().length() != 9){
						ssError.setText("Must be 9 numbers(only) long.");
						quitClick =0;
						saveCheck = false;
					}
					else{
					ssError.setText("No letters.");
					}
					quitClick =0;
					saveCheck = false;
				}
			if(addText.getText().trim().equals("")){
				addError.setText("Please enter address.");
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getAddressCount(addText.getText().trim().toString())){
						addError.setText("No symbols.");
						quitClick =0;
						saveCheck = false;
				}
			
			if(cityText.getText().trim().equals("")){
				cityError.setText("Please enter city.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(cityText.getText().toString())){
							cityError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
							
				}
			if(zipText.getText().trim().equals("")){
				zipError.setText("Please enter zip code.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getSymbolCount(zipText.getText().toString())){
							zipError.setText("No symbols.");
							quitClick =0;
							saveCheck = false;
							
				}
			if(countyText.getText().trim().equals("")){
				countyError.setText("Please enter county.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(countyText.getText().toString())){
							countyError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
							
				}
			if(phoneText.getText().trim().equals("")){
				phoneError.setText("Please enter a phone number");
				quitClick =0;
				saveCheck = false;
				
			}
				else if(getSymbolCount(phoneText.getText().trim().toString())){
						phoneError.setText("No symbols.");
						quitClick =0;
						saveCheck = false;
				}
			
				else if(phoneErrorCheck(phoneText.getText().trim().toString())){
					if (phoneText.getText().trim().toString().length() != 10){
						phoneError.setText("Must be 10 numbers(only) long.");
						quitClick =0;
						saveCheck = false;
					}
					else{
					phoneError.setText("No letters.");
					}
					quitClick =0;
					saveCheck = false;
				}
			try{
				if(dob.getValue().equals("")){
				dobError.setText("This should never appear");
				}
				
			}
			catch(NullPointerException ex){
				dobError.setText("Please enter date of birth.");
				quitClick =0;
				saveCheck = false;
			}
			if(!(genMale.isSelected()||genFemale.isSelected()||otherGen.isSelected())){
				genError.setText("Please pick a gender.");
				quitClick =0;
				saveCheck = false;
			}
		
			
			if(!(needUpdate.isSelected()||upToDate.isSelected())){
				immError.setText("Please Select Immuzation Status");
				quitClick =0;
				saveCheck = false;
			}
			if(emergencyText.getText().trim().equals("")){
				emeError.setText("Need a emergency contact.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(emergencyText.getText().toString())){
							emeError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
			}
			if(relText.getText().trim().equals("")){
				relError.setText("Please enter relationship to person.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getNonLetterCount(relText.getText().toString())){
							relError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
			}
			if(emeConText.getText().trim().equals("")){
				ecnError.setText("Please enter contact number.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(getSymbolCount(emeConText.getText().toString())){
							ecnError.setText("No symbols.");
							quitClick =0;
							saveCheck = false;
			}
			else if(phoneErrorCheck(emeConText.getText().trim().toString())){
				if (emeConText.getText().trim().toString().length() != 10){
					ecnError.setText("Must be 10 numbers(only) long.");
					quitClick =0;
					saveCheck = false;
				}
				else{
				ecnError.setText("No letters.");
				}
				quitClick =0;
				saveCheck = false;
			}

			if(!(inAid.isSelected()||inCare.isSelected()||inPriv.isSelected())){
				careError.setText("Please pick your insurance.");
				quitClick =0;
				saveCheck = false;
			}
			else if(inPriv.isSelected() && insuName.getText().trim().equals("")){
				careError.setText("Please enter Insurance name.");
				quitClick =0;
				saveCheck = false;
			}
			else if(medicareText.getText().trim().equals("")){
				careError.setText("Please enter Insurance Number.");
				quitClick =0;
				saveCheck = false;
			}
		
			try{
				if(appointment.getValue().equals("")){
				appError.setText("This should never appear");
				}
				
			}
			catch(NullPointerException ex){
				appointment.setPromptText(todayDate);
				appError.setText("Please select today if there is no appointment");
				quitClick =0;
				saveCheck = false;
			}

			if(complaint.getText().trim().equals("")){
				cError.setText("Please enter the patients complaint.");
				saveResult.setText("");
				quitClick =0;
				saveCheck = false;
			}
			/* I save all the data into strings to better place them into 
			 * a file for saving later.
			 */
				else if (saveCheck){
					fName = fNameText.getText().toString();
					lName = lNameText.getText().toString();
					comp = complaint.getText().toString();
					ssString = ssText.getText().toString();
					addressString = addText.getText().toString();
					cityString = cityText.getText().toString();
					zipString = zipText.getText().toString();
					countyString = countyText.getText().toString();
					phoneString = phoneText.getText().toString();
					dobString = dob.getValue().toString();
					genderTransfer = (RadioButton)gender.getSelectedToggle();
					genderString = genderTransfer.getText().toString();
					immuTransfer = (RadioButton)immuStatus.getSelectedToggle();
					immuString = immuTransfer.getText();
					emcString = emergencyText.getText().toString();
					relationString = relText.getText().toString();
					ecnString = emeConText.getText().toString();
					insuranceTransfer = (RadioButton)insurance.getSelectedToggle();
					insuranceButtonString =insuranceTransfer.getText().toString();
					if(inPriv.isSelected()){
						insuranceNameString = insuName.getText().toString();
					}
					else{
						insuranceNameString = "";
					}
					insuranceNumberString = medicareText.getText().toString();
					appointString = appointment.getValue().toString();
					
					
					if(isNotRetrieved){
					while(true){		
					idDouble = Math.random() *101;
					id = (idDouble.intValue());
					if(!pList.containsKey(id)){
						break;
					}
					}
					}
					
					pList.put(id, new Patient(fName,lName,ssString,addressString,cityString,zipString,countyString,phoneString,
							dobString,genderString,immuString,emcString,relationString,ecnString,(insuranceButtonString +"\n" + insuranceNameString + "\n" +insuranceNumberString),
							appointString,comp, Integer.toString(id)));
					
					
					saveResult.setTextFill(Color.BLACK);
				
					quitClick =0;

					
					/* I save the information into this file using a try catch method in order to catch the
					 * IOException should there be one.
					 */
					try{
						FileOutputStream file = new FileOutputStream("patient.txt");
						ObjectOutputStream file2= new ObjectOutputStream(file);
						
						file2.writeObject(pList);
						file2.close();
						file.close(); 
						saveResult.setText("Patient number " + id +" has been saved, have a great day!");
						
						
					}
					catch(IOException ex){
						ex.printStackTrace();
					}
					//Should the data have been retrieved the value is switched back to true so the next
					//patient is able to get an id.
					isNotRetrieved =true;
				}
			
	
		});
		/* If the person clicks the find button they will open another window made
		 * for finding a patient
		 */
		find.setOnAction(e->{
			
			
			
			id = PatientFinderMain.startAgain();
			
			System.out.println("Id retrieved: " + id);
			
			
			try{
				FileInputStream fileinput = new FileInputStream("patient.txt");
				ObjectInputStream file2input= new ObjectInputStream(fileinput);
				try{
					if(pList.isEmpty()){
						
					pList = (HashMap) file2input.readObject();
					
					}
				
				}
				catch(EOFException ex1){
					
				}

				
				file2input.close();
			}
			catch(Exception ex){
				System.out.println("Gotta make new file!");
			}
			
		try{
			
			fNameText.setText(pList.get(id).getfName());
			lNameText.setText(pList.get(id).getlName());
			ssText.setText(pList.get(id).getSsn());
			addText.setText(pList.get(id).getAddress());
			cityText.setText(pList.get(id).getCity());
			zipText.setText(pList.get(id).getZipCode());
			countyText.setText(pList.get(id).getCounty());
			phoneText.setText(pList.get(id).getPhone());
			
			//Convert the date of birth into LocalDate to store in the DatePicker
			dob.setValue(LocalDate.parse(pList.get(id).getDateOfBirth()));
			//It checks of the string says Male or Female and selects the correct gender.
			if(pList.get(id).getGender().equals("Male")){
			genMale.setSelected(true);
			}else if(pList.get(id).getGender().equals("Female")){
			genFemale.setSelected(true);
			}else{
			otherGen.setSelected(true);
			}
			//I do the same thing I did for gender for immunization status
			if(pList.get(id).getImmunizationStatus().equals("Up To Date")){
			upToDate.setSelected(true);
			}else{
			needUpdate.setSelected(true);
			}
			emeConText.setText(pList.get(id).getEmergencyContactNumber());
			relText.setText(pList.get(id).getRelationship());
			emergencyText.setText(pList.get(id).getEmergencyContact());
			
			//I split the String for the insurance information into multiple Strings separated by "\n"
			insuranceSplit = pList.get(id).insurance.split("\n");
			//I check which is selected. Once again it is the same as the gender checker above.
			if(insuranceSplit[0].equals("Medicaid")){
			inAid.setSelected(true);
			}else if(insuranceSplit[0].equals("Medicare")){
			inCare.setSelected(true);
			}else{
			inPriv.setSelected(true);
			}
			insuName.setText(insuranceSplit[1]);
			medicareText.setText(insuranceSplit[2]);
			appointment.setValue(null);
			
			/*I delete the elements inside the insuranceSplit array in order
			 * to make sure the array is empty for the next patient retrieval.
			 */
			insuranceSplit = null;
			
			//I set the isNotRetrieved to false because this data was retrieved from a file
			isNotRetrieved = false;
		
			}
		catch(NullPointerException nullex){
			System.out.println("Nope!");
		}
			
			
		});
		
		
		
		/* IF the person clicks the close button the program will close. Any data
		 * not saved will be lost.
		 */
		quit.setOnAction(e->{
			quitClick++;
			saveResult.setFont(Font.font(null,FontWeight.BOLD, 18));
			saveResult.setTextFill(Color.RED);
			
			saveResult.setText("All unsaved data will be lost! Click " + "\"Close\"" + " again to proceed.");
			
			if (quitClick == 2){
			System.exit(0);
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
			complaint.clear();
			appointment.setValue(null);
			saveResult.setText("");
			saveResult.setTextFill(Color.BLACK);
			
			
			
			
		});
		
	}
		

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



	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//Work on or abandon...	
	
/*	
	
	private void setTime(Label clock2) {
		
		Calendar cal = new GregorianCalendar();
		
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int ampm = cal.get(Calendar.AM_PM);
		String day ="";
		
		if(ampm == 1){
			day = "PM";
		}
		else{
			day = "AM";
		}
		
		String time = hour + ":" + minute + ":" + second + " " + day;
		
		clock.setText(time);
		
		
	}*/

}
