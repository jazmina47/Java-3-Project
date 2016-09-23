package health_care_final_project;

public class Patient {
	
	private String firstName;
	private String lastName;
	private String title;
	private String specialty;
	private double salary;

	//Default constructor
	public Patient() {
	
	}

	public Patient(String firstName, String lastName, String title, String specialty, double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.specialty = specialty;
		this.salary = salary;
	}

}
