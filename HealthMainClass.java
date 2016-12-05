package healthcareLook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HealthMainClass {
	
	static AnchorPane anch;
	static Stage primaryStage;
	
	public static void startPatient() {
		
		HealthController control = new HealthController();
		FXMLLoader loads2 = new FXMLLoader();
		loads2.setLocation(HealthMainClass.class.getResource("HealthCare.fxml"));
		try{
			anch = (AnchorPane) loads2.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		primaryStage = new Stage();
		primaryStage.setTitle("HealthCare Clinic Demo");
		Scene scene = new Scene(anch);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void stageClose(){
		primaryStage.close();
	}
	


}
