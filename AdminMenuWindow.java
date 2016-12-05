package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminMenuWindow {

	
	static AnchorPane anch;
	static Stage adminStage;
	static String staffID;
	
	public static void startAdminWindow(String id) {
		staffID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("AdminWindow.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		adminStage = new Stage();
		adminStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		adminStage.setScene(scene);
		adminStage.show();
		
	}
	public static void stageClose(){
		adminStage.close();
	}
	public static String getID(){
		return staffID;
	}
}
