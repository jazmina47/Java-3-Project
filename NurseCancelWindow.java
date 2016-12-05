package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NurseCancelWindow {

	static AnchorPane anch;
	static Stage nurseStage;
	static String patID;
	
	public static void startNurseCancelWindow(String id) {
		patID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("NurseCancel.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		nurseStage = new Stage();
		nurseStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		nurseStage.setScene(scene);
		nurseStage.show();
		
	}
	public static void stageClose(){
		nurseStage.close();
	}
}
