package health_care_final_project;

import java.util.Date;

public class Patient extends Person{
	
	private Date dateOfVisit;
	private String complaint;

	//construct a default Patient object
	public Patient() {
	}

	//construct a Patient object with the specified firstName, lastName, dateOfVisit, and complaint
	public Patient(String firstName, String lastName, Date dateOfVisit, String complaint) {
		
		//inherited methods from Person class
		setFirstName(firstName);
		setLastName(lastName);
		
		//super keyword can be used to invoke the superclass constructor with the specified arguments
		//super(firstName, lastName);
		this.dateOfVisit = dateOfVisit;
		this.complaint = complaint;
	}

	//setters and getters
	public Date getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(Date dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	
}

