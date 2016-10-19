package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HealthLoginStaff {

	
	
	static AnchorPane anchLogin;
	static Stage staffLoginStage;
	
	public static void startLoginScreen() {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("HealthStaff.fxml"));
		try{
			anchLogin = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		staffLoginStage = new Stage();
		staffLoginStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anchLogin);
		staffLoginStage.setScene(scene);
		staffLoginStage.show();
		
	}
	public static void stageClose(){
		staffLoginStage.close();
	}
}
