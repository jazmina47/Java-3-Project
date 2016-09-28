package health_care_final_project;

import java.util.Date;

public class Patient {
	
	private String firstName;
	private String lastName;
	private Date dateOfVisit;
	private String complaint;

	//Default constructor
	public Patient() {
	
	}

	//constructor with arguments 
	public Patient(String firstName, String lastName, Date dateOfVisit, String complaint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfVisit = dateOfVisit;
		this.complaint = complaint;
	}

	//setters and getters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

