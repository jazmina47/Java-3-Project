package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorMenuWindow {

	
	
	static AnchorPane anch;
	static Stage docStage;
	static String staffID;
	
	public static void startDoctorWindow(String id) {
		staffID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("DoctorMenu.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		docStage = new Stage();
		docStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		docStage.setScene(scene);
		docStage.show();
		
	}
	public static void stageClose(){
		docStage.close();
	}
	public static String getID(){
		return staffID;
	}
}

