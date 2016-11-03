package healthcareLook;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

public class ReceptionistLoginController implements Initializable{

	
	

	@FXML
	Button buttonSubmit;
	@FXML
	Button buttonCreateNewStaff;
	@FXML
	TextField staffId;
	@FXML
	TextField staffPassword;
	@FXML
	ImageView errorImage;
	Tooltip errortip;
	Alert testAlert;
	Connection conn;
	Statement sqlState;
	ResultSet rows;
	Alert testAlert2 = new Alert(AlertType.INFORMATION);
	Alert testAlert3 = new Alert(AlertType.INFORMATION);
	
	Optional<ButtonType> result;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		testAlert= new Alert(AlertType.INFORMATION);
		
		buttonSubmit.setOnAction(e->{

			if(staffId.getText().trim().equals("")){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}
			else if(getLetterCount(staffId.getText().trim())){
				errorImage.setVisible(true);
				errortip.install(errorImage, new Tooltip("Please enter your staff ID"));
			}
			//this will check if the staff id exists in the database.
			else if(DatabaseWork..CheckLoginInformation(staffId.getText().trim(), staffPassword.getText().trim())){
				
			
				
				// if staff member has appointments open diagnosis window.
				// an If statement on top to check if a person is admin, if they are it sends them to
				// an admin window.
				
				//if the person is a receptionist this will tell them to log in from the receptionist window.
				if(DatabaseWork.CheckForStaffPosition(staffId.getText().trim()).equals("Receptionist")){
					testAlert.setAlertType(AlertType.WARNING);
					testAlert.setTitle("Staff Login Result");
					testAlert.setHeaderText("You are logged in");
					testAlert.setContentText("");
					testAlert.showAndWait();
					//This is to return the person back to the previous window.
					SecretaryMenuWindow.startSecretaryWindow();
					
					
				}
				else if(DatabaseWork.CheckForStaffPosition(staffId.getText().trim()).equals("Nurse")){
					testAlert2.setAlertType(AlertType.INFORMATION);
					testAlert2.setTitle("Staff Login Result");
					testAlert2.setHeaderText("");
					testAlert2.setContentText("You Successfully logged in!");
					testAlert2.showAndWait();
					
				}
				//if the person is an admin it ask if they wish to add an employee or delete an employee.
				else if(DatabaseWork.CheckForStaffPosition(staffId.getText().trim()).equals("Admin")){
					testAlert2.setAlertType(AlertType.INFORMATION);
					testAlert2.setTitle("Staff Login Result");
					testAlert2.setHeaderText("");
					testAlert2.setContentText("You Successfully logged in!");
					testAlert2.showAndWait();
					
						
					}
				else{
					testAlert2.setAlertType(AlertType.INFORMATION);
					testAlert2.setTitle("Staff Login Result");
					testAlert2.setHeaderText("");
					testAlert2.setContentText("You Successfully logged in!");
					testAlert2.showAndWait();
					//if the person has appointments to add diagnosis to this will send them to the
					//diagnosis window.
					
				}
			}
			else{
				//the id number was not inside the database.
				testAlert3.setAlertType(AlertType.WARNING);
				testAlert3.setTitle("Staff Login Result");
				testAlert3.setHeaderText("Problem!");
				testAlert3.setContentText("ID NUMBER NOT FOUND");
				testAlert3.showAndWait();
			}
			
		});
		
		
	}
	//This check is for when I do not want letters or symbols. 
	public boolean getLetterCount(String s) {	
	     Pattern p = Pattern.compile("[^0-9]");
	     Matcher m = p.matcher(s);
	     boolean b = m.find();
	     return b;
	 }
}
