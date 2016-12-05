package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PatientPaymentWindow {

	
	static AnchorPane anchCreate;
	static Stage createPaymentStage;
	static String patID;
	
	public static void startCreatePaymentWindow(String id) {
		
		patID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("PatientPayment.fxml"));
		try{
			anchCreate = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		createPaymentStage = new Stage();
		createPaymentStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anchCreate);
		createPaymentStage.setScene(scene);
		createPaymentStage.show();
		
	}
	public static void stageClose(){
		createPaymentStage.close();
	}
	
	public static String getID(){
		return patID;
	}
}
