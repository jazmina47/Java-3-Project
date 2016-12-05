package healthcareLook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StaffDiagonsisWindow {

	
	
	static AnchorPane anchCreate;
	static Stage createDiagonsisStage;
	static String staffID;

	public static void startCreateDiagonsisWindow(String id){
		
		staffID = id;
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("StaffDiagonsis.fxml"));
		try{
			anchCreate = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		createDiagonsisStage = new Stage();
		createDiagonsisStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anchCreate);
		createDiagonsisStage.setScene(scene);
		createDiagonsisStage.show();
		
	}

	public static void stageClose(){
		createDiagonsisStage.close();
	}
	
	public static String RetrieveID(String id){
		id = staffID;
		return id;
	}
	
	

}
