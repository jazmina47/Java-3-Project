package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StaffInteractionWindow {

	
	static AnchorPane anch;
	static Stage sInteractionStage;
	static String idNumber = "5782";
	
	public static void startSInteraction(String id) {
		
		idNumber = id;
		FXMLLoader loads2 = new FXMLLoader();
		loads2.setLocation(HealthMainClass.class.getResource("StaffInteraction.fxml"));
		
		try{
			anch = (AnchorPane) loads2.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		sInteractionStage = new Stage();
		sInteractionStage.setTitle("HealthCare Clinic Demo");
		Scene scene = new Scene(anch);
		sInteractionStage.setScene(scene);
		sInteractionStage.show();
		
	}
	
	public static String getID(String id){
		id = idNumber;
		return id;
	}
	
}
