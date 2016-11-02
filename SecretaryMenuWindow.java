package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SecretaryMenuWindow {

	
	
	static AnchorPane anchCreate;
	static Stage createStaffStage;
	
	public static void startSecretaryWindow() {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("SecretaryMenu.fxml"));
		try{
			anchCreate = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		createStaffStage = new Stage();
		createStaffStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anchCreate);
		createStaffStage.setScene(scene);
		createStaffStage.show();
		
	}
	public static void stageClose(){
		createStaffStage.close();
	}
}
