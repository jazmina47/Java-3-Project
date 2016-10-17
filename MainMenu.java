package healthcareLook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenu extends Application{

	AnchorPane anch;
	static Stage mainStage;
	
	public void start(Stage MainStage) {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(HealthMainClass.class.getResource("MainMenu.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		mainStage = new Stage();
		mainStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		mainStage.setScene(scene);
		mainStage.show();
		
	}
	public static void stageClose(){
		mainStage.close();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
