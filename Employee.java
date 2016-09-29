package health_care_final_project;

public class Employee extends Person{
	
	//data fields 
	private String title;
	private String specialty;
	private double salary;
	
	//construct a default Employee object
	public Employee() {		
	}

	//construct an Employee object with the specified firstName, lastName, title, specialty, and salary
	public Employee(String firstName, String lastName, String title, String specialty, double salary) {
		
		//inherited methods from Person class
		setFirstName(firstName);
		setLastName(lastName);
		
		//super keyword can be used to invoke the superclass constructor with the specified arguments
		//super(firstName, lastName);
		this.title = title;
		this.specialty = specialty;
		this.salary = salary;
	}

	//setters and getters
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
