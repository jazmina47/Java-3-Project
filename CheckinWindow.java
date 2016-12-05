package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CheckinWindow {

	
	

	static AnchorPane anchLogin;
	static Stage checkinStage;
	static String patID =" ";
	
	public static void startCheckinScreen(String id) {
		patID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("Checkin.fxml"));
		try{
			anchLogin = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		checkinStage = new Stage();
		checkinStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anchLogin);
		checkinStage.setScene(scene);
		checkinStage.show();
		
	}
	public static void stageClose(){
		checkinStage.close();
	}
	public static String getID(){
		return patID;
	}
}
