package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorCancelWindow {

	
	static AnchorPane anch;
	static Stage docCancelStage;
	static String patID;
	
	public static void startDoctorCancelWindow(String id) {
		patID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("DoctorCancel.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		docCancelStage = new Stage();
		docCancelStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		docCancelStage.setScene(scene);
		docCancelStage.show();
		
	}
	public static void stageClose(){
		docCancelStage.close();
	}
}
