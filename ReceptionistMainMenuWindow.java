package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReceptionistMainMenuWindow{


	static AnchorPane anch;
	static Stage recepStage;
	
	public static void startReceptionstMenu() {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("ReceptionistMainMenu.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		recepStage = new Stage();
		recepStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		recepStage.setScene(scene);
		recepStage.show();
		
	}
	public static void stageClose(){
		recepStage.close();
	}
	

}

