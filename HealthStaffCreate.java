package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HealthStaffCreate {

	
	static AnchorPane anchCreate;
	static Stage createStaffStage;
	
	public static void startCreateWindow() {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("HealthCreateStaff.fxml"));
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