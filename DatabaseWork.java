package healthcareLook;

/*This class was made to do all the work for the database.
 * This means retrieving and storing data.
 * Add way to verify if person is admin
 * Delete Staff from admin window (would this mess with the other tables?)
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseWork {

	
	static Connection conn = null;
	static PreparedStatement prepareState= null;
	static String command = null;
	static ResultSet rows = null;
	static ResultSet rows2 = null;
	static ResultSet patidcheck = null;
	static ResultSet getidstaff = null;
	static ResultSet retreievestaff = null;
	static ResultSet retreievepatient = null;
	static Statement sqlState = null;
	static Statement sqlState2 = null;
	static Employee employeeData = null;
	static Patient patientData = null;
	static String idNumber = null;
	static ArrayList<Appointment> patientId = new ArrayList<Appointment>();
	
	
	public static Boolean IDCheckPatient(String id){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select patient_id from patient where patient_id = '" + id + "'";
			
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
			if(rows.next()){
				return true;
			}

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public static Boolean IDCheckStaff(String id){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select staff_id from employee where staff_id = '" + id +"'";
			
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
			if(rows.next()){
				return true;
			}

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public static Patient IDPatientConfirmation(String id){
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select * from patient where patient_id = '" + id +"'";
			
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
			if(rows.next()){
			//	System.out.print("getting patient info");
				patientData = new Patient(rows.getString(1), rows.getString(2),rows.getString(3),rows.getString(4),rows.getString(5),rows.getString(6),rows.getString(7),rows.getString(8),rows.getString(9)
						,rows.getString(10),rows.getString(11),rows.getString(12),rows.getString(13),rows.getString(14),rows.getString(15));
			}

		
			
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return patientData;
		
		
	}
	
	public static Employee IDStaffConfirmation(String id){
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select * from employee where staff_id = '" + id +"'";
			
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
			if(rows.next()){
				System.out.print("getting patient info");
				employeeData = new Employee(rows.getString(1), rows.getString(2),rows.getString(3),rows.getString(4),rows.getString(5),rows.getString(6),rows.getString(7),rows.getString(8),rows.getString(9)
						,rows.getString(10),rows.getString(11),rows.getString(12),rows.getString(13));
			}

		
			
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return employeeData;
		
		
	}
	
	public static Boolean SavePatient(Patient pat){	
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			prepareState = conn.prepareStatement("insert into patient (first_name , last_name , ssn, address, city, zip_code, county, phone, date_of_birth, gender, immunization_status, emergency_contact, emergency_contact_relationship, emergency_contact_number, insurance, patient_id) " + "values(?, ? , ?, ? , ?, ?, ? , ?, ? , ?, ?, ? , ?, ?, ?, ?)");
			prepareState.setString(1, pat.getfName());
			prepareState.setString(2, pat.getlName());
			prepareState.setString(3, pat.getSsn());
			prepareState.setString(4, pat.getAddress());
			prepareState.setString(5, pat.getCity());
			prepareState.setString(6, pat.getZipCode());
			prepareState.setString(7, pat.getCounty());
			prepareState.setString(8, pat.getPhone());
			prepareState.setString(9, pat.getDateOfBirth());
			prepareState.setString(10, pat.getGender());
			prepareState.setString(11, pat.getImmunizationStatus());
			prepareState.setString(12, pat.getEmergencyContact());
			prepareState.setString(13, pat.getRelationship());
			prepareState.setString(14, pat.getEmergencyContactNumber());
			prepareState.setString(15, pat.getInsurance());
			prepareState.setNull(16, java.sql.Types.INTEGER);
			int add = prepareState.executeUpdate();

			return true;
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
			return false;
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	public static String RetrieveIDPatient(String ssn){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
	//		System.out.println("Getting id");
			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select * from patient where ssn = '" + ssn +"'";
			
			retreievepatient = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
			if(retreievepatient.next()){
				idNumber = retreievepatient.getString(16);
			}

			
			
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return idNumber;
	}
	
public static Boolean SaveStaff(Employee pat){
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			prepareState = conn.prepareStatement("insert into employee (first_name , last_name , ssn, address, city, zip_code, county, phone, date_of_birth, gender, staff_position, staff_speciality, staff_salary, staff_id) " + "values(?, ? , ?, ? , ?, ?, ? , ?, ? , ?, ?, ? , ?, ?)");
			prepareState.setString(1, pat.getfName());
			prepareState.setString(2, pat.getlName());
			prepareState.setString(3, pat.getSsn());
			prepareState.setString(4, pat.getAddress());
			prepareState.setString(5, pat.getCity());
			prepareState.setString(6, pat.getZipCode());
			prepareState.setString(7, pat.getCounty());
			prepareState.setString(8, pat.getPhone());
			prepareState.setString(9, pat.getDateOfBirth());
			prepareState.setString(10, pat.getGender());
			prepareState.setString(11, pat.getPosition());
			prepareState.setString(12, pat.getSpeciality());
			prepareState.setString(13, pat.getSalary());
			prepareState.setNull(14, java.sql.Types.INTEGER);
			
			
			int add = prepareState.executeUpdate();

			
			return true;
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
			return false;
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
		
	}

	public static void RemoveStaff(String id){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Delete from employee where staff_id = '" + id +"'";
			
			sqlState.executeUpdate(command);

			
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}




	public static String RetrieveIDStaff(String ssn){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select * from employee where ssn = '" + ssn +"'";
			
			retreievestaff = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
			if(retreievestaff.next()){
				idNumber = retreievestaff.getString(14);
			}

			
			
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return idNumber;
	}


	public static Boolean SetAppointmentPatient(String id,String complain, String day, String doc, String time) {
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			
			if(doc.isEmpty()){
				prepareState = conn.prepareStatement("insert into appointment (appointment_date, appointment_time, complaint , patient_id, requested_doctor, staff_id, checkin) " + "values(?, ?, ?, ?, ?, ?, ?)");
				prepareState.setString(1, day);
				prepareState.setString(2, time);
				prepareState.setString(3, complain);
				prepareState.setString(4, id);
				prepareState.setNull(5, java.sql.Types.VARCHAR);
				prepareState.setNull(6, java.sql.Types.VARCHAR);
				prepareState.setString(7, "N");
				
			}
			else{
				prepareState = conn.prepareStatement("insert into appointment (appointment_date, appointment_time, complaint, patient_id, requested_doctor, staff_id, checkin) " + "values(?, ?, ? , ?, ?, ?, ?)");
				prepareState.setString(1, day);
				prepareState.setString(2, time);
				prepareState.setString(3, complain);
				prepareState.setString(4, id);
				prepareState.setString(5, doc);
				prepareState.setNull(6, java.sql.Types.VARCHAR);
				prepareState.setString(7, "N");
				
			}

			int add = prepareState.executeUpdate();

		
			return true;
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
			return false;
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
		
	}


	public static boolean CheckAvailability(String appointmentTime, String id, String appointmentDate) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select appointment_date, appointment_time, staff_id from appointment where appointment_date = '" + appointmentDate + "' && appointment_time = '" + appointmentTime + "' && staff_id = '" + id  + "'";
		//	System.out.println("I have the command!");
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
	//		System.out.println("Did I find something?!");
			if(rows.next()){
		//		System.out.println("UH OH YOU GOT PROBLEM!");
				rows.getString(1);
				return false;
			}

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	//	System.out.println("Guess there was nothing!");
		
		return true;
		
	}


	public static boolean UpdateAppointment(String id, String id2, String date, String time) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "update appointment set staff_id = '" + id + "' where patient_id = '" + id2 +"' AND appointment_date = '" + date +"' AND appointment_time = '" + time +"'";
			
			int update = sqlState.executeUpdate(command);

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
			return false;
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
		
		return true;
		
	}


	public static boolean CheckAvailabilityStaff(String string, String staffID, String appointment) {
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select appointment_date, appointment_time, staff_id from appointment where appointment_date = '" + appointment + "' && appointment_time = '" + string + "' && staff_id = '" + staffID  + "'";
	//		System.out.println("I have the command!");
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
	//		System.out.println("Did I find something?!");
			if(rows.next()){
		//		System.out.println("UH OH YOU GOT PROBLEM!");
				rows.getString(1);
				return false;
			}

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	//	System.out.println("Guess there was nothing!");
		
		return true;

	}


	public static String CheckForStaff(String doctor) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			command = "select staff_id from employee where first_name = '" + doctor + "' OR last_name = '" + doctor + "'";

	//		System.out.println("I have the command!");
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
	//		System.out.println("Did I find something?!");
			if(rows.next()){
	//			System.out.println("WE GOT A MATCH!!!!");
				doctor = rows.getString(1);
			
				return doctor;
			}

			else{
				doctor = null;
				return doctor;
			}
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
//		System.out.println("Guess I found nothing..");
		

		
		
		
		
		
		
		return null;
	}


	public static boolean CheckAvailabilityPatient(String appointmentTime, String patID, String appointmentDate) {

		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select appointment_date, appointment_time, patient_id from appointment where appointment_date = '" + appointmentDate + "' && appointment_time = '" + appointmentTime + "' && patient_id = '" + patID  + "'";
	//		System.out.println("I have the command!");
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
	//		System.out.println("Did I find something?!");
			if(rows.next()){
	//			System.out.println("UH OH YOU GOT PROBLEM!");
				rows.getString(1);
				return false;
			}

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	//	System.out.println("Guess there was nothing!");
		
		return true;

	}


	public static boolean HasAppointmentsToFile(String idNumber) {
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			sqlState2 = conn.createStatement();
			String command = "Select appointment_date, appointment_time, patient_id from appointment where staff_id = '" + idNumber + "'";
			rows = sqlState.executeQuery(command);
			while(rows.next()){
				patientId.add(new Appointment(rows.getString(1), rows.getString(2), rows.getString(3)));
	//			System.out.println("got patient id data");
			}
			
			
			
			for(int i = 0; i< patientId.size(); i++){
				String command2 = "Select appointment_date, appointment_time from payment_bill where patient_id = '" + patientId.get(i).getPatient_id() + "'";
		//		System.out.println("inside for loop");
				rows2 = sqlState2.executeQuery(command2);
				while(rows2.next()){
		//			System.out.println("inside while loop");
						if(patientId.get(i).getAppointmentDate().equals(rows2.getString(1)) && patientId.get(i).getAppointmentTime().equals(rows2.getString(2))){
	
						}
						else{
							return true;
						}
				}
			}
			
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
	//		System.out.println("Did I find something?!");
			

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public static String CheckForStaffPosition(String id){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select staff_position from employee where staff_id = '" + id +"'";
			
			rows = sqlState.executeQuery(command);
			//I return the position of the employer. This position can be a doctor, or receptionist.
			if(rows.next()){
				return rows.getString(1);
			}

			
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return "no position";
		
		
	}
	
	public static boolean HasPendingBill(String id){
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcare_clinic?autoReconnect=true&useSSL=false","root", "CSC3610" );
			sqlState = conn.createStatement();
			
			String command = "Select diagnosis from payment_bill where patient_id = '" + id + "' AND paid = 'N'";
	//		System.out.println("I have the command!");
			rows = sqlState.executeQuery(command);
			//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
	//		System.out.println("Did I find something?!");
			if(rows.next()){
				return true;
			}

		
		}
		catch(SQLException ex){
			System.out.println("SQLException : " +ex.getMessage());
			System.out.println("VendorError : " +ex.getErrorCode());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
//		System.out.println("Guess there was nothing!");
		
		return false;

		
		
	}
	
	
	
	
	
	
}






























