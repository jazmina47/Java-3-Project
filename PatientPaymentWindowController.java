package healthcareLook;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Should appear if the person has a bill to pay.
public class PatientPaymentWindowController implements Initializable{

	@FXML
	Button submit;
	@FXML
	Button quit;
	@FXML
	RadioButton cash;
	@FXML
	RadioButton credit;
	@FXML
	ListView list;
	@FXML
	Label paymentTotal;
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
	
	Connection conn;
	Statement sqlState;
	ResultSet rows;
	Statement sqlState2;
	ResultSet rows2;
	Statement sqlState3;
	ResultSet rows3;
	Statement sqlState4;
	ResultSet rows4;
	
	ArrayList<String> appDate = new ArrayList<String>();
	ArrayList<String> appTime = new ArrayList<String>();
	ArrayList<String> appDia = new ArrayList<String>();
	ArrayList<String> appPay = new ArrayList<String>();
	ArrayList<String> listSelected = new ArrayList<String>();
	@FXML
	ToggleGroup toggle = new ToggleGroup();
	
	Alert warnAlert = new Alert(AlertType.WARNING);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	
	
	String stringApp, stringTime, stringDia, stringPay, patID;
	int convertor;
	int totalAmount;
	int listcount = 0;
	int intPay;
	int monthtest;
	int yeartest;
	boolean nextlist = false;
	boolean isdone = true;
	boolean isPaidFor;
	Calendar cal = Calendar.getInstance();
	//cash.setToggleGroup(toggle);
	//credit.setToggleGroup(toggle);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//I set the toggle group for the radio buttons as well as retrieve the patient id.
	//	System.out.println("Beginning of payment window");
		
		patID = PatientPaymentWindow.getID();
		//I open a connection to the database.
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
	//		System.out.println(patID);
			String command = "Select diagnosis, appointment_date, appointment_time, payment from payment_bill where patient_id = '" + patID + "' AND paid = 'N'"; 
	//		System.out.println("payment window inside!");
			rows = sqlState.executeQuery(command);
	//		System.out.println("Please work!");
			//while data was found
			while(rows.next()){
				//I wish to add the data into arraylists for each section.
	//			System.out.println("inside row loop!");
					appDate.add(rows.getString(2));
					appTime.add(rows.getString(3));
					appDia.add(rows.getString(1));
					appPay.add(rows.getString(4));
					
					listcount++;
			}
			//I insert all of the dates and times into the Listview.
			for(int i = 0; i < appDate.size();i++){
			/*	System.out.println(appDate);
				System.out.println(appTime);
				System.out.println(appDia);
				System.out.println(appPay); */
				list.getItems().add(appDate.get(i) + " @ " + appTime.get(i));
		//		System.out.println(list.getItems());
			}
		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		//I set listview to mutliple selection mode
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		/*
		 * WORK ON LATER IF YOU CAN.
		 */
		//When the listview is selected.
		list.setOnMouseClicked(a->{
			tip.install(list, new Tooltip("When the total selected goes from 2 to 1, please make sure you reselect "
					+ "the one you want."));
			listSelected.clear();
			//Make a arraylist that will add the selected indices.
			listSelected.addAll(list.getSelectionModel().getSelectedIndices());
	//		System.out.println(listSelected + "Before the if statement");
			if(listSelected.size() == 1){
				listSelected.clear();
				listSelected.add(Integer.toString(list.getSelectionModel().getSelectedIndex()));
			}
			paymentTotal.setText("");
			stringDia = "";
			intPay =0;
	//		System.out.println(listSelected);
			//This for loop is to add up the diagnosis that were selected as well 
			//as the costs of them all together.
			for(int h=0; h< listSelected.size(); h++){
			convertor = Integer.parseInt(String.valueOf((listSelected.get(h))));

			try{
					stringDia += appDia.get(convertor) + ", ";
					intPay += Integer.parseInt(appPay.get(convertor));
			}
			catch(ArrayIndexOutOfBoundsException ax){
	//			System.out.println(convertor);
			}
			}
			
			stringDia += "Cost: $" + intPay +".00";
			
			paymentTotal.setText(stringDia);
		});
		//This checks of there was any items selected or not and throws a warning if there was none.
		submit.setOnAction(b ->{
	//		System.out.println("Button pressed!");
			if(list.getSelectionModel().getSelectedItems().isEmpty()){
				warnAlert.setTitle("Error");
				warnAlert.setHeaderText("There was a problem!");
				warnAlert.setContentText("Please have one item in the list selected.\n If list is empty then please press skip button.");
				warnAlert.showAndWait();
			}
			//If cash was selected the database will change saying you paid it.
			else{
				if(cash.isSelected()){
		//			System.out.println("cash selected pressed!");
					//Change the data in the database to paid
					String command;
					for(int h=0; h< listSelected.size(); h++){
						convertor = Integer.parseInt(String.valueOf((listSelected.get(h))));

						try{
							Class.forName("com.mysql.jdbc.Driver");

							conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
							sqlState = conn.createStatement();
							
							command = "update payment_bill set paid = 'Y' where appointment_date = '" +appDate.get(convertor) +"' AND appointment_time = '" +appTime.get(convertor)+ "' AND diagnosis = '" +appDia.get(convertor)+"' AND patient_id = '" +patID+"'";
							
							sqlState.executeUpdate(command);
							
							//Auto update the list after the payment is done.
							if(list.getSelectionModel().getSelectedIndices().size() == 1){
								list.getItems().remove(list.getSelectionModel().getSelectedIndex());
							}
							else{
							list.getItems().removeAll(list.getSelectionModel().getSelectedIndices());
							}
							dataAlert.setTitle("Successfully Paid");
							dataAlert.setHeaderText("The transaction was successful.");
							dataAlert.setContentText("Thank you for paying your bill.");
							dataAlert.showAndWait();
							
						}
						catch(SQLException ex){
							System.out.println("SQLException : " +ex.getMessage());
							System.out.println("VendorError : " +ex.getErrorCode());
						}
						catch(ClassNotFoundException e){
							e.printStackTrace();
						}
					}
					
				}
				//else it will open a credit card input window and take your credit card information
				
				else{
		//			System.out.println("CREDIT CARD SELECTED!!");
					//This goes to the credit card window but will return a boolean if the person does enter the information or not.
					isPaidFor = CreditCardWindow.start();
					String command;
					if(isPaidFor){
						try{
							Class.forName("com.mysql.jdbc.Driver");

							conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
							sqlState = conn.createStatement();
							
							//This updates the bill table to 'Y' for the paid attribute.
							command = "update payment_bill set paid = 'Y' where appointment_date = '" +appDate.get(convertor) +"' AND appointment_time = '" +appTime.get(convertor)+ "' AND diagnosis = '" +appDia.get(convertor)+"' AND patient_id = '" +patID+"'";
							
							sqlState.executeUpdate(command);
							
							//Auto update the list after the payment is done.
							if(list.getSelectionModel().getSelectedIndices().size() == 1){
								list.getItems().remove(list.getSelectionModel().getSelectedIndex());
							}
							else{
							list.getItems().removeAll(list.getSelectionModel().getSelectedIndices());
							}
							dataAlert.setTitle("Successfully Paid");
							dataAlert.setHeaderText("The transaction was successful.");
							dataAlert.setContentText("Thank you for paying your bill.");
							dataAlert.showAndWait();
							
							
						}
						catch(SQLException ex){
							System.out.println("SQLException : " +ex.getMessage());
							System.out.println("VendorError : " +ex.getErrorCode());
						}
						catch(ClassNotFoundException e){
							e.printStackTrace();
						}
					}
					

				}
			}
		});
		
		quit.setOnAction(o->{
			//Takes the user to the main menu window
			PatientPaymentWindow.stageClose();
		});
		
	}

}


























