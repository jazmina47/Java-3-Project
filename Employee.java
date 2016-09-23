package health_care_final_project;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String title;
	private String specialty;
	private String salary;
	
	//default constructor 
	public Employee() {	
	}

	//constructor with arguments 
	public Employee(String firstName, String lastName, String title, String specialty, String salary) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.specialty = specialty;
		this.salary = salary;
	}


}
