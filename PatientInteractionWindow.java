package healthcareLook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PatientInteractionWindow {

	
	static AnchorPane anch;
	static Stage pInteractionStage;
	static String patientID = null;
	
	public static void startPInteraction(String id) {
		
		
		patientID = id;
		HealthController control = new HealthController();
		FXMLLoader loads2 = new FXMLLoader();
		loads2.setLocation(HealthMainClass.class.getResource("PatientInteraction.fxml"));
		try{
			anch = (AnchorPane) loads2.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		pInteractionStage = new Stage();
		pInteractionStage.setTitle("HealthCare Clinic Demo");
		Scene scene = new Scene(anch);
		pInteractionStage.setScene(scene);
		pInteractionStage.show();
		
	}
	
	public static String GetID(String id){
		id = patientID;
		
		return id;
	}

	public static void WindowClose() {
		pInteractionStage.close();
		
	}


	
}
