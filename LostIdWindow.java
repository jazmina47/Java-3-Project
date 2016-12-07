package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LostIdWindow {

	

	static AnchorPane anch;
	static Stage lostStage;
	
	public static void startLostWindow() {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("LostId.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		lostStage = new Stage();
		lostStage.getIcons().add(new Image("https://image.freepik.com/free-icon/lifeline-in-a-heart-outline_318-48031.jpg"));
		lostStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		lostStage.setScene(scene);
		lostStage.show();
		
	}
	public static void stageClose(){
		lostStage.close();
	}

}
