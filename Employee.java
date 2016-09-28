package health_care_final_project;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String title;
	private String specialty;
	private double salary;
	
	//default constructor 
	public Employee() {	
		
	}

	//constructor with arguments 
	public Employee(String firstName, String lastName, String title, String specialty, double salary) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.specialty = specialty;
		this.salary = salary;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}

