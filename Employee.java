package healthcareLook;

import java.io.Serializable;

public class Employee extends Person implements Serializable{
	
	String position;
	String speciality;
	String salary;
	
	
	//default constructor 
	public Employee() {	
	}


	public Employee(String fName, String lName, String ssn, String address, String city, String zipCode, String county,
			String phone, String dateOfBirth, String gender,String position, String speciality,
			String salary) {
		super(fName, lName, ssn, address, city, zipCode, county, phone, dateOfBirth, gender);
		this.position = position;
		this.speciality = speciality;
		this.salary = salary;
	}





	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getSpeciality() {
		return speciality;
	}


	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}


	/*public String toString(){
		//Working on now. comment out.
		return "Employee Information
	}

	*/
	


}