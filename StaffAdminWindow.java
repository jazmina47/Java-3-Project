package healthcareLook;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*a window exclusive to the admin, where this will add or delete employees.
 * 
 */
public class StaffAdminWindow {

	
	
	AnchorPane anch;
	static Stage admintage;
	
	public void startAdminWindow(Stage adminStage) {
		
		FXMLLoader loadMain = new FXMLLoader();
		loadMain.setLocation(MainMenu.class.getResource("StaffAdmin.fxml"));
		try{
			anch = (AnchorPane) loadMain.load();
		
		}
		catch(IOException ex1){
			
			ex1.printStackTrace();
			
		}
		admintage = new Stage();
		admintage.setTitle("Welcome to Healhcare Clinic!");
		Scene scene = new Scene(anch);
		admintage.setScene(scene);
		admintage.show();
		
	}
	public static void stageClose(){
		admintage.close();
	}
}
