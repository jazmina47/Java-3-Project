package healthcareLook;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
/*
 * The purpose of this window is to add new employees.
 */
public class StaffCreateController implements Initializable{
	
	@FXML
	TextField staffFirstName;
	@FXML
	TextField staffLastName;
	@FXML
	TextField staffSsn;
	@FXML
	TextField staffCity;
	@FXML
	TextField staffCounty;
	@FXML
	TextField staffPhone;
	@FXML
	TextField staffOfficeNumber;
	@FXML
	TextField staffSpeciality;
	@FXML
	TextField staffZip;
	@FXML
	TextField staffAddress;
	@FXML
	DatePicker staffDob;
	@FXML
	RadioButton staffMale;
	@FXML
	RadioButton staffFemale;
	@FXML
	RadioButton staffOther;
	@FXML
	Button staffClear;
	@FXML
	Button staffSave;
	Tooltip stafftip;
	@FXML
	ImageView fWarning;
	@FXML
	ImageView lWarning;
	@FXML
	ImageView cityWarning;
	@FXML
	ImageView countyWarning;
	@FXML
	ImageView phoneWarning;
	@FXML
	ImageView officeWarning;
	@FXML
	ImageView addressWarning;
	@FXML
	ImageView zipWarning;
	@FXML
	ImageView dobWarning;
	@FXML
	ImageView positionWarning;
	@FXML
	ImageView genderWarning;
	@FXML
	ImageView ssnWarning;
	@FXML
	ImageView specialityWarning;
	@FXML
	ComboBox staffPosition;

	boolean isSafeToSave = true;
	boolean isDobError = false;
	
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	Optional<ButtonType> result;
	Alert warnAlert = new Alert(AlertType.WARNING);
	
	Employee newStaff;
	
	RadioButton genderTransfer;
	ToggleGroup genderToggle = new ToggleGroup();
	
	String stringFirstName, stringLastName, stringSsn,stringCity,stringCounty,stringPhone,stringOfficeNumber,stringSpeciality,
	stringZip,stringAddress,stringDob,stringGender, stringSalary,adminId;


	
	

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
	staffPosition.getItems().addAll("Receptionist", "Nurse", "Doctor","Admin");
	
	staffSave.setOnAction(e ->{
		//this boolean is set to true since we would believe the data is OK to send.
		isSafeToSave = true;
		//the if/else statements check if the data really is ok to send. if there isnt then there will be warnings
		//and the boolean will be set to false to prevent the data from saving.
		if(staffFirstName.getText().trim().equals("")){
			fWarning.setVisible(true);
			stafftip.install(fWarning, new Tooltip("Please enter a first name."));
			
			//This is in case the user wants to save the data
			//I want to remind them again that the data could be loss so
			//I reset the count to zero so it can start at 1 next time they click close
			isSafeToSave = false;
			
		}
			else if(getNonLetterCount(staffFirstName.getText().trim().toString())){
				fWarning.setVisible(true);
				stafftip.install(fWarning, new Tooltip("No numbers or symbols."));
					isSafeToSave = false;
					
			}
			else{
				fWarning.setVisible(false);
			}
		if(staffLastName.getText().trim().equals("")){
			lWarning.setVisible(true);
			stafftip.install(lWarning, new Tooltip("Please enter a last name."));
			
			//This is in case the user wants to save the data
			//I want to remind them again that the data could be loss so
			//I reset the count to zero so it can start at 1 next time they click close
			isSafeToSave = false;
			
		}
			else if(getNonLetterCount(staffLastName.getText().trim().toString())){
				lWarning.setVisible(true);
				stafftip.install(lWarning, new Tooltip("No numbers or symbols."));
					isSafeToSave = false;
					
			}
			else{
				lWarning.setVisible(false);
			}
		if(staffSsn.getText().trim().equals("")){
			ssnWarning.setVisible(true);
			stafftip.install(ssnWarning, new Tooltip("Please enter SSN"));
			isSafeToSave = false;
			
		}
			else if(getSymbolCount(staffSsn.getText().trim().toString())){
				ssnWarning.setVisible(true);
				stafftip.install(ssnWarning, new Tooltip("No symbols."));
					isSafeToSave = false;
			}
		
			else if(ssnErrorCheck(staffSsn.getText().trim().toString())){
				if (staffSsn.getText().trim().toString().length() != 9){
					ssnWarning.setVisible(true);
					stafftip.install(ssnWarning, new Tooltip("Must be 9 numbers(only) long."));
					isSafeToSave = false;
				}
				else{
					ssnWarning.setVisible(true);
					stafftip.install(ssnWarning, new Tooltip("No letters."));
				}
				isSafeToSave = false;
			}
			else{
				ssnWarning.setVisible(false);
			}
		if(staffAddress.getText().trim().equals("")){
			addressWarning.setVisible(true);
			stafftip.install(addressWarning, new Tooltip("Please enter address."));
			isSafeToSave = false;
			
		}
			else if(getAddressCount(staffAddress.getText().trim().toString())){
				addressWarning.setVisible(true);
				stafftip.install(addressWarning, new Tooltip("No symbols."));
					isSafeToSave = false;
			}
			else{
				addressWarning.setVisible(false);
			}
		
		if(staffCity.getText().trim().equals("")){
			cityWarning.setVisible(true);
			stafftip.install(cityWarning, new Tooltip("Please enter city."));
			isSafeToSave = false;
			
		}
		else if(getNonLetterCount(staffCity.getText().toString())){
			cityWarning.setVisible(true);
			stafftip.install(cityWarning, new Tooltip("No numbers or symbols."));
						isSafeToSave = false;
						
			}
		else{
			cityWarning.setVisible(false);
		}
		if(staffZip.getText().trim().equals("")){
			zipWarning.setVisible(true);
			stafftip.install(zipWarning, new Tooltip("Please enter zip code."));
			isSafeToSave = false;
			
		}
		else if(getSymbolCount(staffZip.getText().toString())){
			zipWarning.setVisible(true);
			stafftip.install(zipWarning, new Tooltip("No symbols."));
						isSafeToSave = false;
						
			}
		else{
			zipWarning.setVisible(false);
		}
		if(staffCounty.getText().trim().equals("")){
			countyWarning.setVisible(true);
			stafftip.install(countyWarning, new Tooltip("Please enter county."));
			isSafeToSave = false;
			
		}
		else if(getNonLetterCount(staffCounty.getText().toString())){
			countyWarning.setVisible(true);
			stafftip.install(countyWarning, new Tooltip("No numbers or symbols."));
						isSafeToSave = false;
						
			}
		else{
			countyWarning.setVisible(false);
		}
		if(staffPhone.getText().trim().equals("")){
			phoneWarning.setVisible(true);
			stafftip.install(phoneWarning, new Tooltip("Please enter a phone number"));
			isSafeToSave = false;
			
		}
			else if(getSymbolCount(staffPhone.getText().trim().toString())){
				phoneWarning.setVisible(true);
				stafftip.install(phoneWarning, new Tooltip("No symbols."));
					isSafeToSave = false;
			}
		
			else if(phoneErrorCheck(staffPhone.getText().trim().toString())){
				phoneWarning.setVisible(true);
				if (staffPhone.getText().trim().toString().length() != 10){
					stafftip.install(phoneWarning, new Tooltip("10 numbers(only)."));
					isSafeToSave = false;
				}
				else{
					phoneWarning.setVisible(true);
					stafftip.install(phoneWarning, new Tooltip("No letters."));
				}
				isSafeToSave = false;
			}
			else{
				phoneWarning.setVisible(false);
			}
		isDobError=false;
			try{
				if(staffDob.getValue().equals("")){
				}
			}
			catch(NullPointerException ex){
				dobWarning.setVisible(true);
				stafftip.install(dobWarning, new Tooltip("Please enter date of birth."));
				isSafeToSave = false;
				isDobError= true;
			}
		if(!isDobError){
			dobWarning.setVisible(false);
		}
		if(!(staffMale.isSelected()||staffFemale.isSelected()||staffOther.isSelected())){
			genderWarning.setVisible(true);
			stafftip.install(genderWarning, new Tooltip("Please pick a gender."));
			isSafeToSave = false;
		}
		else{
			genderWarning.setVisible(false);
		}
		
		if(staffSpeciality.getText().trim().equals("")){
			specialityWarning.setVisible(true);
			stafftip.install(specialityWarning, new Tooltip("Please state your speciality."));
			isSafeToSave = false;
		}
		else{
			specialityWarning.setVisible(false);
		}
		if(staffPosition.getSelectionModel().isEmpty()){
			positionWarning.setVisible(true);
			stafftip.install(positionWarning, new Tooltip("Please state your position."));
			isSafeToSave = false;
		}
		else{
			positionWarning.setVisible(false);
		}
		
		//if the data was ok to send then they are all converted to strings and a salary is made.
		if(isSafeToSave){
			stringFirstName = staffFirstName.getText().trim();
			stringLastName = staffLastName.getText().trim();
			stringSsn = staffSsn.getText().trim();
			stringCity = staffCity.getText().trim();
			stringCounty = staffCounty.getText().trim();
			stringPhone = staffPhone.getText().trim();
			stringOfficeNumber = staffOfficeNumber.getText().trim();
			stringSpeciality = staffSpeciality.getText().trim();
			stringZip = staffZip.getText().trim();
			stringAddress = staffAddress.getText().trim();
			stringDob = staffDob.getValue().toString().trim();
			genderTransfer = (RadioButton)genderToggle.getSelectedToggle();
			stringGender = genderTransfer.getText();
			stringSalary = Integer.toString((int)((Math.random()*600000)+ 600000));
			
			//this creates a new employee type.
			newStaff = new Employee(stringFirstName,stringLastName,stringSsn,stringAddress,stringCity,stringZip,stringCounty,
					stringPhone,stringDob,stringGender,staffPosition.getSelectionModel().getSelectedItem().toString(),staffSpeciality.getText().trim(),stringSalary);
			
			
			//This saves to the database.
			boolean isSaved = DatabaseWork.SaveStaff(newStaff);
			if(isSaved){
				//If it was saved successfully then the id number was made. this displays after we retrieve it and 
				//is written on the staff members card. For us we would have to write it down or check the database.
				String idNumber = DatabaseWork.RetrieveIDStaff(newStaff.getSsn());
				confirmAlert.setTitle("Data Successfully Saved!");
				confirmAlert.setHeaderText(idNumber);
				confirmAlert.setContentText("Please write down and remember your id number.\nDo you wish to create another patient?");
				
				ButtonType yes = new ButtonType("YES");
				ButtonType no = new ButtonType("NO");
				confirmAlert.getButtonTypes().setAll(yes, no);
				result = confirmAlert.showAndWait();
				//if there is no more staff to add then we move to the interaction window.
				if(result.get() == no){
				
					adminId = HealthStaffCreate.GetAdminId(adminId);
					HealthLoginStaff.stageClose();
					HealthStaffCreate.stageClose();
					confirmAlert.close();
				}
				else{
					//if there is more staff members to add then
					//this will clear all the information so you can
					//enter another staff member
					staffFirstName.clear();
					staffLastName.clear();
					staffCity.clear();
					staffSsn.clear();
					staffAddress.clear();
					staffZip.clear();
					staffCounty.clear();
					staffDob.setValue(null);
					staffPhone.clear();
					staffOfficeNumber.clear();
					staffMale.setSelected(false);
					staffFemale.setSelected(false);
					staffOther.setSelected(false);
					staffSpeciality.clear();
					staffPosition.getSelectionModel().clearSelection();
					confirmAlert.close();
				}
			}
			else{
				//IF the data was not saved this will appear.
				warnAlert.setTitle("Error in saving data");
				warnAlert.setHeaderText("Warning!");
				warnAlert.setContentText("If you already have an account with us please log in");
				warnAlert.showAndWait();
			}

		}
		
		staffClear.setOnAction(f ->{
			
			//This button clears all the textfields and date pickers. It also deselects the radio buttons.
			staffFirstName.clear();
			staffLastName.clear();
			staffCity.clear();
			staffSsn.clear();
			staffAddress.clear();
			staffZip.clear();
			staffCounty.clear();
			staffDob.setValue(null);
			staffPhone.clear();
			staffOfficeNumber.clear();
			staffMale.setSelected(false);
			staffFemale.setSelected(false);
			staffOther.setSelected(false);
			staffSpeciality.clear();
			staffPosition.getSelectionModel().clearSelection();

		});
			
	});

	
	
}


}
