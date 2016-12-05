package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NurseNotesWindow {

	
	

	static AnchorPane anch;
	static Stage notesStage;
	static String patID;
	public static void startNotesWindow(String id) {
		patID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("NurseNotes.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		notesStage = new Stage();
		notesStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		notesStage.setScene(scene);
		notesStage.show();
		
	}
	public static void stageClose(){
		notesStage.close();
	}
	public static String getID(){
		return patID;
	}
}
