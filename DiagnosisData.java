package healthcareLook;

public class DiagnosisData {

	
	String Diagnosis_name;
	String Diagnosis_cost;
	
	DiagnosisData(){
		
	}

	public DiagnosisData(String diagnosis_name, String diagnosis_cost) {
		super();
		Diagnosis_name = diagnosis_name;
		Diagnosis_cost = diagnosis_cost;
	}

	public String getDiagnosis_name() {
		return Diagnosis_name;
	}

	public void setDiagnosis_name(String diagnosis_name) {
		Diagnosis_name = diagnosis_name;
	}

	public String getDiagnosis_cost() {
		return Diagnosis_cost;
	}

	public void setDiagnosis_cost(String diagnosis_cost) {
		Diagnosis_cost = diagnosis_cost;
	}
	
	
}
