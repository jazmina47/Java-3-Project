package healthcareLook;
/*NOT SURE WHAT TO ADD/DELETE...
 * 
 */
import java.io.Serializable;

public class Patient extends Person implements Serializable{
	

	String immunizationStatus;
	String emergencyContact;
	String relationship;
	
	String emergencyContactNumber;
	String insurance;
	String appointmentDate;
	String complaint;
	
	public Patient(){
		
	}



	public Patient(String fName, String lName, String ssn, String address, String city, String zipCode, String county,
			String phone, String dateOfBirth, String gender, String id, String immunizationStatus,
			String emergencyContact, String relationship, String emergencyContactNumber, String insurance,
			String appointmentDate, String complaint) {
		super(fName, lName, ssn, address, city, zipCode, county, phone, dateOfBirth, gender, id);
		this.immunizationStatus = immunizationStatus;
		this.emergencyContact = emergencyContact;
		this.relationship = relationship;
		this.emergencyContactNumber = emergencyContactNumber;
		this.insurance = insurance;
		this.appointmentDate = appointmentDate;
		this.complaint = complaint;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getImmunizationStatus() {
		return immunizationStatus;
	}


	public void setImmunizationStatus(String immunizationStatus) {
		this.immunizationStatus = immunizationStatus;
	}


	public String getEmergencyContact() {
		return emergencyContact;
	}


	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}


	public String getRelationship() {
		return relationship;
	}


	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}


	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}


	public String getInsurance() {
		return insurance;
	}


	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}


	public String getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public String getComplaint() {
		return complaint;
	}


	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	

	public String toString(){
		return "\nPatient Information:\nFirst Name: " +fName+"\nLast Name: " + lName+ 
				"\nSocial Security Number: "+ssn+"\nAddress: "+ address+"\nCity: " +city+
				"\nZip Code: "+zipCode+ "\nCounty: " +county+ "\nPhone: "+phone+
				"\nDate of Birth: "+dateOfBirth+ "\nGender: "+gender+ "\nImmunization Status: " +immunizationStatus+
				"\nEmergency Contact: " +emergencyContact+"\nRelationship: "+relationship+ 
				"\nEmergency Contact Number: "+emergencyContactNumber+ "\nInsurance Details: "+insurance+ 
				"\nAppointment Date: "+appointmentDate+ "\nPatient Complaints: " +complaint +"\n" + "ID #: " + id +"\n___________\n";
	}
	
}

