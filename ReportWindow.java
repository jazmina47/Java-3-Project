package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReportWindow {

	
	static AnchorPane anch;
	static Stage reportStage;
	
	public static void startReportWindow() {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("Report.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		reportStage = new Stage();
		reportStage.getIcons().add(new Image("https://image.freepik.com/free-icon/lifeline-in-a-heart-outline_318-48031.jpg"));
		reportStage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		reportStage.setScene(scene);
		reportStage.show();
		
	}
	public static void stageClose(){
		reportStage.close();
	}
}
