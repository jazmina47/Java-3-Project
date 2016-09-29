package health_care_final_project;

public class Person {
	
	//data fields 
	private String firstName;
	private String lastName;

	//construct a default Person object
	public Person() {
	}

	//construct a Person object with the specified firstName and lastName
	public Person(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	/*@Override
	public String toString(){
		return "First Name: " + getFirstName() + "," + " Last Name: " + getLastName();
	}*/
	
}
