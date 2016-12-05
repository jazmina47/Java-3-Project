package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StaffDeleteWindow {

	
	static AnchorPane anch;
	static Stage deleteStaffStage;
	static String adminId;
	
	public static void startDeletionWindow(String id) {
		adminId = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("StaffDelete.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		deleteStaffStage = new Stage();
		deleteStaffStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		deleteStaffStage.setScene(scene);
		deleteStaffStage.show();
		
	}
	
	public static void stageClose(){
		deleteStaffStage.close();
	}
	
	public static String getId(){
		return adminId;
	}
}
