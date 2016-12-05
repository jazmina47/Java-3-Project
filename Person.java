package healthcareLook;

public class Person {
	
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
	
	public Person(){
		
	}
	

	public Person(String fName, String lName, String ssn, String address, String city, String zipCode, String county,
			String phone, String dateOfBirth, String gender) {
		super();
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
	


	
}
