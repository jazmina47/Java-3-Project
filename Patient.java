package healthcareLook;
/*NOT SURE WHAT TO ADD/DELETE, FOR NOW LEAVE ALONE!
 * 
 */
import java.io.Serializable;

public class Patient implements Serializable{
	
	String fName;
	String lName;
	String ssn;
	String address;
	String city;
	String zipCode;
	String county;
	String phone;
	String dateOfBirth;
	String gender;
	
	String immunizationStatus;
	String emergencyContact;
	String relationship;
	
	String emergencyContactNumber;
	String insurance;
	String appointmentDate;
	String complaint;
	String patientId;
	
	public Patient(){
		
	}

	
	public Patient(String fName, String lName, String ssn, String address, String city, String zipCode,String county, String phone,
			String dateOfBirth, String gender, String immunizationStatus, String emergencyContact, String relationship,
			String emergencyContactNumber, String insurance, String appointmentDate, String complaint, String patientId) {
		
		this.fName = fName;
		this.lName = lName;
		this.ssn = ssn;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.county = county;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.immunizationStatus = immunizationStatus;
		this.emergencyContact = emergencyContact;
		this.relationship = relationship;
		this.emergencyContactNumber = emergencyContactNumber;
		this.insurance = insurance;
		this.appointmentDate = appointmentDate;
		this.complaint = complaint;
		this.patientId = patientId;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
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
	

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String toString(){
		return "\nPatient Information:\nFirst Name: " +fName+"\nLast Name: " + lName+ 
				"\nSocial Security Number: "+ssn+"\nAddress: "+ address+"\nCity: " +city+
				"\nZip Code: "+zipCode+ "\nCounty: " +county+ "\nPhone: "+phone+
				"\nDate of Birth: "+dateOfBirth+ "\nGender: "+gender+ "\nImmunization Status: " +immunizationStatus+
				"\nEmergency Contact: " +emergencyContact+"\nRelationship: "+relationship+ 
				"\nEmergency Contact Number: "+emergencyContactNumber+ "\nInsurance Details: "+insurance+ 
				"\nAppointment Date: "+appointmentDate+ "\nPatient Complaints: " +complaint +"\n" + "ID #: " + patientId +"\n___________\n";
	}
	
}
