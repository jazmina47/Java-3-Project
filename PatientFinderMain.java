package healthcareLook;
/*This class will be for the second window
 * This window will find the patient by looking 
 * through the file.
 */

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PatientFinderMain{
	
	static AnchorPane anchor2;
	static Stage primaryStage2;
	static int patId;
	
	public static int startAgain() {
		
		
		primaryStage2 = new Stage();
		FXMLLoader loadPatient = new FXMLLoader();
		loadPatient.setLocation(HealthMainClass.class.getResource("PatientFinder.fxml"));
		try{
			anchor2 = (AnchorPane) loadPatient.load();
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		primaryStage2.setTitle("HealthCare Finder Demo");
		Scene sceneFinder = new Scene(anchor2);
		primaryStage2.setScene(sceneFinder);
		primaryStage2.showAndWait();
		
		
	
	//	patId = control.getID();

		System.out.println("PatId: " + patId);
		primaryStage2.close();
		return patId;
	
	}
	
	public static void stageClose(){
		primaryStage2.close();
	}
	
	public static void IdHolder(int id){
		patId = id;
		System.out.println("PatId inside method: " + patId);
		
	}
//	public static void main(String[] args) {
	//	launch(args);
	//}

}
