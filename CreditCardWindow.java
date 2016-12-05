package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreditCardWindow {

	
	
	static AnchorPane anch;
	static Stage creditStage;
	static boolean ispaid = false;
	
	public static boolean start() {
		ispaid = false;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("CreditCard.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		creditStage = new Stage();
		creditStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		creditStage.setScene(scene);
		creditStage.showAndWait();
		//If the person leaves the window before this changes from false to true then it would return a false always.
		return ispaid;
	}
	public static void stageClose(){
		creditStage.close();
	}
	
	public static void SetPaid(boolean dataPaid){
		ispaid = dataPaid;
		
	}
	


}


