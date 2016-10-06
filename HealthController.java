package healthcareLook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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


/*The purpose of this program is to save and load paitent data
 * this will use FileOutputStream and FileInputStream to bring and amend data.
 * I use SceneBuilder for the entire GUI.
 * 
 */

/*ADD PLACE FOR ID NUMBER
 * SOMETHING FOR EMPLOYEES TO LOG IN
 * SOMETHING FOR SOCIAL SECURITY
 * SOMETHING FOR PHONE NUMBER
 * ID BADGE NUMBER
 * ADDRESS
 * APPOINTMENT
 */

public class HealthController {
	
	//I create variables for labels, buttons, textfields, and strings
	
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
	Label fNameConfirmation;
	@FXML
	Label lNameConfirmation;
	@FXML
	Label ssConfirmation;
	@FXML
	Label addConfirmation;
	@FXML
	Label confirmOrError;
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
	Button confirmButton;
	@FXML
	Button paymentButton;
	@FXML
	Button findSpecialist;
	@FXML	
	Button quit2;
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
	TextField iDFinder;
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
	RadioButton genMale;
	@FXML
	RadioButton genFemale;
	@FXML
	RadioButton genOther;
	@FXML
	RadioButton inAid;
	@FXML
	RadioButton inCare;
	@FXML
	RadioButton inPriv;
	@FXML
	DatePicker dob;
	@FXML
	DatePicker appointment;
	ToggleGroup gender = new ToggleGroup();
	ToggleGroup insurance = new ToggleGroup();
	@FXML
	Label testClock = new RealClock();
	
	//If there are no errors saveCheck will remain true and the data will be saved. but if there is one problem
	//The data will change saveCheck to false.
	
	boolean saveCheck = true;
	String errorDetails;
	
	int quitClick;
	
	AnchorPane anchor2;
	Stage stagePatientFinder;
	
	
	// This is for java swing. I use java swing for the other windows I open from the Main window.
	// The main window is made from SceneBuilder.
	
	
	HashMap<Integer,Patient> pList = new HashMap<Integer,Patient>();
	HashMap<Integer,Employee> sList = new HashMap<Integer,Employee>();
	
	String fName,lName,comp,diag,patientData, addressString, ssString;
	
	int id;
	Date date = new Date();
	Calendar today = new GregorianCalendar();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String todayDate;
	


	@FXML
	public void Intialize(){
		
		genMale.setToggleGroup(gender);
		genFemale.setToggleGroup(gender);
		inPriv.setToggleGroup(insurance);
		inAid.setToggleGroup(insurance);
		inCare.setToggleGroup(insurance);

		
		
		
		todayDate = format.format(date);
		
		Stage stagePatientFinder = new Stage();
		

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
				else if(errorCheck(fNameText.getText().toString())){
						fError.setText("No numbers or symbols.");
						quitClick =0;
						saveCheck = false;
						
						
				}
				
			if(lNameText.getText().trim().equals("")){
					lError.setText("Please enter the last name.");
					quitClick =0;
					saveCheck = false;
					
				}
				else if(errorCheck(lNameText.getText().toString())){
								lError.setText("No numbers or symbols.");
								quitClick =0;
								saveCheck = false;
								
					}
			if(ssText.getText().trim().equals("")){
				ssError.setText("Please enter SSN");
				quitClick =0;
				saveCheck = false;
				
			}
				else if(errorCheck2(ssText.getText().trim().toString())){
						ssError.setText("No symbols.");
						quitClick =0;
						saveCheck = false;
				}
			
				else if(errorCheck3(ssText.getText().trim().toString())){
					if (ssText.getText().trim().toString().length() != 9){
						ssError.setText("Must be 9 characters long.");
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
				else if(errorCheck2(addText.getText().trim().toString())){
						addError.setText("No symbols.");
						quitClick =0;
						saveCheck = false;
				}
			
			if(cityText.getText().trim().equals("")){
				cityError.setText("Please enter city.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(errorCheck(cityText.getText().toString())){
							cityError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
							
				}
			if(zipText.getText().trim().equals("")){
				zipError.setText("Please enter zip code.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(errorCheck2(zipText.getText().toString())){
							zipError.setText("No symbols.");
							quitClick =0;
							saveCheck = false;
							
				}
			if(countyText.getText().trim().equals("")){
				countyError.setText("Please enter county.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(errorCheck(countyText.getText().toString())){
							countyError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
							
				}
			if(phoneText.getText().trim().equals("")){
				phoneError.setText("Please enter a phone number");
				quitClick =0;
				saveCheck = false;
				
			}
				else if(errorCheck2(phoneText.getText().trim().toString())){
						phoneError.setText("No symbols.");
						quitClick =0;
						saveCheck = false;
				}
			
				else if(errorCheck4(phoneText.getText().trim().toString())){
					if (phoneText.getText().trim().toString().length() != 10){
						phoneError.setText("Must be 10 characters long.");
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
			
			if(!(genMale.isSelected()||genFemale.isSelected()|| genOther.isSelected())) {
				genError.setText("Please pick a gender.");
				quitClick =0;
				saveCheck = false;
			}
			
			
			if(immuText.getText().trim().equals("")){
					immError.setText("Please fill the field.");
					quitClick =0;
					saveCheck = false;
					
				}
				else if(errorCheck(immuText.getText().toString())){
								immError.setText("No numbers or symbols.");
								quitClick =0;
								saveCheck = false;
				}
			if(emergencyText.getText().trim().equals("")){
				emeError.setText("Need a emergency contact.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(errorCheck(emergencyText.getText().toString())){
							emeError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
			}
			if(relText.getText().trim().equals("")){
				relError.setText("Please enter relationship to person.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(errorCheck(relText.getText().toString())){
							relError.setText("No numbers or symbols.");
							quitClick =0;
							saveCheck = false;
			}
			if(emeConText.getText().trim().equals("")){
				ecnError.setText("Please enter contact number.");
				quitClick =0;
				saveCheck = false;
				
			}
			else if(errorCheck2(emeConText.getText().toString())){
							ecnError.setText("No symbols.");
							quitClick =0;
							saveCheck = false;
			}
			else if(errorCheck4(emeConText.getText().trim().toString())){
				if (emeConText.getText().trim().toString().length() != 10){
					ecnError.setText("Must be 10 characters long.");
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
				cError.setText("Please enter the paitents complaint.");
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
					
					saveResult.setTextFill(Color.BLACK);
					saveResult.setText("Data is Saved on paitent.txt, have a great day!");
					quitClick =0;
	
				//This is ignored until the other word is finished. to avoid making errors or mess.	
	
				/*	try{
						FileOutputStream file = new FileOutputStream("paitent.txt", true);
						ObjectOutputStream file2= new ObjectOutputStream(file);
						
						file2.writeObject(pList);
						file2.close();
						file.close(); 
						saveResult.setText("Data is Saved on paitent.txt, have a great day!");
					}
					catch(IOException ex){
						ex.printStackTrace();
					} */
				}
			
	
		});
		/* If the person clicks the find button they will open another window made
		 * for finding a patient
		 */
		find.setOnAction(e->{
			
		
			
			
			FXMLLoader loadFinder = new FXMLLoader();
			loadFinder.setLocation(HealthMainClass.class.getResource("PatientFinder.fxml"));
			try{
				anchor2 = (AnchorPane) loadFinder.load();
			}
			catch(IOException ex1){
				
				ex1.printStackTrace();
				
			}
			stagePatientFinder.setTitle("HealthCare Finder Demo");
			Scene sceneFinder = new Scene(anchor2);
			stagePatientFinder.setScene(sceneFinder);
			stagePatientFinder.show();
			
			
			
			
			
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
		
	}
		
		@FXML 
		public void Initialize2(){
			
			/*This button is for  confirming if the information gathered is correct or not. 
			 * If it is correct then the data will be inserted into another window when you 
			 * hit the confirm button again
			 */
			confirmOrError.setWrapText(true);
			confirmButton.setOnAction(e->{
				
				//This will take the user to the payment main window again to set an appointment, or to pay.
			});
			
			quit2.setOnAction(e->{
				quitClick++;
				confirmOrError.setFont(Font.font(null,FontWeight.BOLD, 18));
				confirmOrError.setTextFill(Color.RED);
				
				confirmOrError.setText("All unsaved data will be lost! Click " + "\"Close\"" + " again to proceed.");
				
				if (quitClick == 2){
				System.exit(0);
				}
			});
		}
		
		/* This check is for when all I want
		 * is letters and no numbers or symbols.
		 * Mostly used for names.
		 */
		

	public boolean errorCheck(String test){
		
		if(test.contains("1") ||
				test.contains("2") ||
				test.contains("3") ||
				test.contains("4") ||
				test.contains("5") ||
				test.contains("6") ||
				test.contains("7") ||
				test.contains("8") ||
				test.contains("9") ||
				test.contains("0") ||
				test.contains("!") ||
				test.contains("@") ||
				test.contains("#") ||
				test.contains("$") ||
				test.contains("%") ||
				test.contains("^") ||
				test.contains("&") ||
				test.contains("*") ||
				test.contains("(") ||
				test.contains(")") ||
				test.contains("_") ||
				test.contains("-") ||
				test.contains("+") ||
				test.contains("=") ||
				test.contains("[") ||
				test.contains("]") ||
				test.contains("{") ||
				test.contains("}") ||
				test.contains("\\") ||
				test.contains("|") ||
				test.contains(":") ||
				test.contains("\"") ||
				test.contains("'") ||
				test.contains("<") ||
				test.contains(">") ||
				test.contains("?") ||
				test.contains(",") ||
				test.contains(".") ||
				test.contains("/") ||
				test.contains("`") ||
				test.contains("~")){
			
			
			return true;
		}
		else{
			return false;
		}
				
	}
	/* This check is for when I want them to enter
	 * numbers but not symbols.
	 */
	
	public boolean errorCheck2(String test2){
		
		if (test2.contains("!") ||
				test2.contains("@") ||
				test2.contains("#") ||
				test2.contains("$") ||
				test2.contains("%") ||
				test2.contains("^") ||
				test2.contains("&") ||
				test2.contains("*") ||
				test2.contains("(") ||
				test2.contains(")") ||
				test2.contains("_") ||
				test2.contains("-") ||
				test2.contains("+") ||
				test2.contains("=") ||
				test2.contains("[") ||
				test2.contains("]") ||
				test2.contains("{") ||
				test2.contains("}") ||
				test2.contains("\\") ||
				test2.contains("|") ||
				test2.contains(":") ||
				test2.contains("\"") ||
				test2.contains("'") ||
				test2.contains("<") ||
				test2.contains(">") ||
				test2.contains("?") ||
				test2.contains(",") ||
				test2.contains(".") ||
				test2.contains("/") ||
				test2.contains("`") ||
				test2.contains("~")){
			
			return true;
		}
		else {
			return false;
		}
	}
	/* this check is for making sure
	 * the data received is just numbers.
	 * If the person adds a letter it will
	 * throw a NumberFormatException which returns true
	 * and if the length of the string is not 9
	 * the try returns true. If everything is ok
	 * then the try returns false.
	 */
	
	public boolean errorCheck3(String test3){
		
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
	 * numbers. Same as errorCheck3.
	 */
	public boolean errorCheck4(String test4){
		
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
