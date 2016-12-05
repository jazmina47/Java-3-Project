package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CancelWindow {

	
	
	static AnchorPane anchLogin;
	static Stage cancelStage;
	static String patID2 = " ";
	
	public static void startCancelScreen(String id) {
		//System.out.println("before start " + patID2);
	//	System.out.println("before start id receieved " + id);
		
		patID2 = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("Cancel.fxml"));
		try{
			anchLogin = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		cancelStage = new Stage();
		cancelStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anchLogin);
		cancelStage.setScene(scene);
		cancelStage.show();
		
	}
	public static void stageClose(){
		cancelStage.close();
	}
	public static String getID(){
	//	System.out.println("getting id " + patID2);
		return patID2;
		
	}
}
