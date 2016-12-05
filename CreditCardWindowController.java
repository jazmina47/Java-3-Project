package healthcareLook;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class CreditCardWindowController implements Initializable{
	@FXML
	TextField cardNumber;
	@FXML
	TextField cardName;
	@FXML
	TextField cvvcode;
	@FXML
	ComboBox month;
	@FXML
	ComboBox year;
	@FXML
	ComboBox cardType;
	@FXML
	Button cardSubmit;
	@FXML
	ImageView creditError;
	Tooltip tip;
	
	boolean isdone = true;
	Date today = Calendar.getInstance().getTime();
	int monthtest;
	int yeartest;
	boolean ispaid = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] format = today.toString().split(" ");
		switch(format[1]){
		case "Aug": monthtest = 8;
		break;
		case "Sep": monthtest = 9;
		break;
	
		case "Oct": monthtest = 10;
		break;
	
		case "Nov": monthtest = 11;
		break;
	
		case "Dec": monthtest = 12;
		break;
	
		case "Jan": monthtest = 1;
		break;
	
		case "Feb": monthtest = 2;
		break;
	
		case "Mar": monthtest = 3;
		break;
	
		case "Apr": monthtest = 4;
		break;
	
		case "May": monthtest = 5;
		break;
	
		case "Jun": monthtest = 6;
		break;
	
		case "Jul": monthtest = 7;
		break;
		
		}
		yeartest = Integer.parseInt(format[5]);
		

		for(int yearcount = 2000; yearcount< 2107; yearcount++){
			
			year.getItems().add(Integer.toString(yearcount));
		}
		
		month.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
		
		cardType.getItems().addAll("Visa", "Discover", "MasterCard", "American Express");
		
		cardType.getSelectionModel().selectFirst();
		month.getSelectionModel().selectFirst();
		year.getSelectionModel().selectLast();
		
		cardSubmit.setOnAction(c->{
			isdone= true;
			//This checks if all the data has been entered. and stops if the data has not all been entered correctly
			if(cardName.getText().trim().equals("")){
				isdone = false;
				
				tip.install(creditError, new Tooltip("Please fill in all Fields with the appropriate data."));
				creditError.setVisible(true);
			}
			else{
				creditError.setVisible(false);
			}
			if(cardNumber.getText().trim().equals("")){
				isdone = false;
				tip.install(creditError, new Tooltip("Please fill in all Fields with the appropriate data."));
				creditError.setVisible(true);
			}
			else{
				creditError.setVisible(false);
			}
			if(cvvcode.getText().trim().equals("")){
				isdone = false;
				tip.install(creditError, new Tooltip("Please fill in all Fields with the appropriate data."));
				creditError.setVisible(true);
			}
			else{
				creditError.setVisible(false);
			}
			//This checks if the card is expired. this compares to the current month and year.
			if(Integer.parseInt(month.getSelectionModel().getSelectedItem().toString()) < monthtest && Integer.parseInt(year.getSelectionModel().getSelectedItem().toString()) < yeartest){
				isdone = false;
				tip.install(creditError, new Tooltip("The card is expired. Please select another card."));
				creditError.setVisible(true);
				//Card is expired
			}
			else{
				creditError.setVisible(false);
			}
			
			if(isdone){
				//If everything is good to go then the window will return a true and the window closes.
				CreditCardWindow.SetPaid(ispaid);
				CreditCardWindow.stageClose();
			}
			
		});
		
		
	}

	
	
	
	
	
	
	
}
