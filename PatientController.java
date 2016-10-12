package healthcareLook;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PatientController {
	
	
	@FXML
	TextField iDFinder;
	@FXML
	Button clearFinder;
	@FXML
	Label ssConfirmation;
	@FXML
	Label lNameConfirmation;
	@FXML
	Label addConfirmation;
	@FXML
	Label fNameConfirmation;
	@FXML
	Button quit2;
	@FXML
	Label confirmOrError;
	@FXML
	Button confirmButton;

	int patientConfirm;
	int quitClick;
	int id;
	
	Patient patientHolder;
	HashMap<Integer,Patient> pList = new HashMap<Integer,Patient>();
	
	@FXML 
	public void Initialize2(){
		
		/*This button is for  confirming if the information gathered is correct or not. 
		 * If it is correct then the data will be inserted into another window when you 
		 * hit the confirm button again
		 */
		confirmOrError.setWrapText(true);

		
		quit2.setOnAction(e->{
			patientConfirm = 0;
			quitClick++;
			confirmOrError.setFont(Font.font(null,FontWeight.BOLD, 18));
			confirmOrError.setTextFill(Color.RED);
			
			confirmOrError.setText("All unsaved data will be lost! Click " + "\"Close\"" + " again to proceed.");
			
			if (quitClick == 2){

				PatientFinderMain.stageClose();
			}
		});
		
		clearFinder.setOnAction(e->{
			fNameConfirmation.setText("First Name");
			lNameConfirmation.setText("Last Name");
			ssConfirmation.setText("999-99-9999");
			addConfirmation.setText("Address");
			confirmOrError.setTextFill(Color.BLACK);
			confirmOrError.setText("");
			patientConfirm = 0;
			quitClick =0;
			iDFinder.clear();
			
		});
		
		
		
		confirmButton.setOnAction(e->{
			
			confirmOrError.setTextFill(Color.BLACK);
			quitClick = 0;
			confirmOrError.setText("");
			
			try{
				FileInputStream fileinput = new FileInputStream("patient.txt");
				ObjectInputStream file2input= new ObjectInputStream(fileinput);
				try{
					pList.clear();
						
					pList = (HashMap) file2input.readObject();
					
				
				}
				catch(EOFException ex1){
					
				}

				
				file2input.close();
			}
			catch(Exception ex){
				System.out.println("File is here!!");
			}
		
			/*This will check if the patient confirm is only one
			 * this makes sure that you can see the information before 
			 * the program retrieves the rest and displays
			 */
			
			if(patientConfirm == 0){
				fNameConfirmation.setText("First Name");
				lNameConfirmation.setText("Last Name");
				ssConfirmation.setText("999-99-9999");
				addConfirmation.setText("Address");
				
				/*This try catch is for making sure the id is an integer
				 * if the person enters something that is not an integer the program will
				 * throw an NumberFormatException, which will check if the textfield has 
				 * anything inside it or if the person left it empty ("") <- empty.
				 * 
				 */
				try{
				id = Integer.parseInt(iDFinder.getText().toString());
				
				if(pList.containsKey(id)){
					
					fNameConfirmation.setText(pList.get(id).getfName());
					lNameConfirmation.setText(pList.get(id).getlName());
					ssConfirmation.setText(pList.get(id).getSsn());
					addConfirmation.setText(pList.get(id).getAddress());
					confirmOrError.setText("Is this your information? (hit enter to confirm)");
					patientConfirm++;
				}
				else{
					confirmOrError.setText("Patient ID not found.");
					patientConfirm =0;
				}

				
				}
				catch(NumberFormatException exe){
					if (iDFinder.getText().toString().trim().equals("")){
						confirmOrError.setText("Please enter the patient id number.");
					}
					else{
						confirmOrError.setText("Please only enter patient id number.");
					}
					
				}
				
			

			}
			
			else if(patientConfirm ==1){
				confirmOrError.setTextFill(Color.BLACK);
				confirmOrError.setText("Please Go back to the HealthCare Clinic Main Screen (do not hit close button)");
				PatientFinderMain.IdHolder(id);
				PatientFinderMain.stageClose();
				System.out.println("id: " + id);
				
				
				
				
			}
			
			
			
			
		});
		
		
	}

	
	public int getID(){
		
		System.out.println("getID method: "  );
		return id;
		
	}
}

















