package healthcareLook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HealthMainClass extends Application{
	
	AnchorPane anch;
	
	public void start(Stage primaryStage) {
		
		
		FXMLLoader loads2 = new FXMLLoader();
		loads2.setLocation(HealthMainClass.class.getResource("HealthCare.fxml"));
		try{
			anch = (AnchorPane) loads2.load();
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		primaryStage.setTitle("HealthCare Clinic Demo");
		
		Scene scene = new Scene(anch);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	

	public static void main(String[] args) {
		launch(args);
	}

}
