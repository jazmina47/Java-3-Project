package healthcareLook;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ReportWindowController implements Initializable{
	
	@FXML
	ListView docList;
	@FXML
	ListView patList;
	@FXML
	Label docCount;
	@FXML
	Label patCount;
	@FXML
	Button save;
	String staffId;
	String patId;
	
	Alert warnAlert = new Alert(AlertType.WARNING);
	Alert dataAlert = new Alert(AlertType.INFORMATION);
	
	ArrayList<String> docs = new ArrayList<String>();
	ArrayList<String> pats = new ArrayList<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		docs.addAll(DatabaseWork.getDoctors());
		docList.getItems().addAll(docs);
		
		docList.setOnMouseClicked(e->{
			staffId = docList.getSelectionModel().getSelectedItem().toString();
			pats.clear();
			
			pats.addAll(DatabaseWork.getPatients(staffId));
			if(!patList.getItems().isEmpty()){
				patList.getItems().clear();
			}
			patList.getItems().addAll(pats);
			
			docCount.setText(" ");
			docCount.setText(DatabaseWork.getPatientCount(staffId));
			
		});
		
		patList.setOnMouseClicked(e->{
			patId = patList.getSelectionModel().getSelectedItem().toString();
			
			patCount.setText(DatabaseWork.getVisitCount(patId));
		});
		
		save.setOnAction(e->{
			if(docList.getSelectionModel().isEmpty() || patList.getSelectionModel().isEmpty()){
				warnAlert.setTitle("Warning!");
				warnAlert.setHeaderText("Please select a doctor and a patient files to save.");
				warnAlert.showAndWait();
			}
			else{
				DatabaseWork.TextFileSave(staffId, patId);
				dataAlert.setTitle("Data saved");
				dataAlert.setHeaderText("Data has been saved to text file.");
				dataAlert.showAndWait();
			}
		});
	}

}
