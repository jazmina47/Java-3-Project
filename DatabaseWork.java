package healthcareLook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	
	public static Boolean IDCheckStaff(String id){
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
	
				conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcareclinic_db?autoReconnect=true&useSSL=false","root", "Agemodel3!" );
				sqlState = conn.createStatement();
				
				String command = "Select emp_id from employee where emp_id = '" + Integer.parseInt(id) +"'";
				
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
	public static Employee IDStaffConfirmation(String id){
			
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
	
				conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcareclinic_db?autoReconnect=true&useSSL=false","root", "Agemodel3!" );
				sqlState = conn.createStatement();
				
				String command = "Select * from employee where emp_id = '" + Integer.parseInt(id) +"'";
				
				rows = sqlState.executeQuery(command);
				//If data was found there will be a row to move the cursor to. Otherwise it will not return a true.
				if(rows.next()){
					System.out.print("getting patient info");
				//	employeeData = new Employee(rows.getString(1), rows.getString(2),rows.getString(3),rows.getString(4),rows.getString(5),rows.getString(6),rows.getString(7),rows.getString(8),rows.getString(9)
					//		,rows.getString(10),rows.getString(11),rows.getString(12),rows.getString(13));
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
public static String CheckForStaffPosition(String id){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcareclinic_db?autoReconnect=true&useSSL=false","root", "Agemodel3!" );
			sqlState = conn.createStatement();
			
			String command = "Select title from employee where emp_id = '" + Integer.parseInt(id) +"'";
			
			rows = sqlState.executeQuery(command);
			//I return the position of the employer. This position can be a doctor, or receptionist.
			if(rows.next()){
				System.out.println(rows.getString(1));
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
public static boolean CheckLoginInformation(String id, String pass) {
	try{
		Class.forName("com.mysql.jdbc.Driver");

		conn= DriverManager.getConnection("jdbc:mysql://localhost/healthcareclinic_db?autoReconnect=true&useSSL=false","root", "Agemodel3!" );
		sqlState = conn.createStatement();
		
		String command = "Select Specialty from employee where emp_id = '" + Integer.parseInt(id) +"' and password = '" + pass + "'";
		
		rows = sqlState.executeQuery(command);
		//I return the position of the employer. This position can be a doctor, or receptionist.
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
}
