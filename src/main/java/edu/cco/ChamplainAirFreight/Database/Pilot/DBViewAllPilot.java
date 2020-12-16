package edu.cco.ChamplainAirFreight.Database.Pilot;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;

/**
 * 
 * @author Kelly
 * @Date Nov 11, 2020
 * @Description - class that pulls DBConnection in order to view all the pilot information, using the View_All_Pilot 
 * stored procedure
 * @TEST_STATUS - confirmed
 */

public class DBViewAllPilot extends DBConnection{
	
	//variables
	public CallableStatement callable = null; 
	private ArrayList<Integer> pilotID = new ArrayList<>(); 
	private ArrayList<String> firstName = new ArrayList<>(); 
	private ArrayList<String> lastName = new ArrayList<>(); 
	private ArrayList<Date> dob = new ArrayList<>(); 
	private ArrayList<String> employeeNumber = new ArrayList<>(); 
	private ArrayList<Date> dateOfHire = new ArrayList<>(); 
	private ArrayList<Date> dateLeftCAF = new ArrayList<>(); 
	
	
	
	/**
	 * Default Constructor
	 * Kelly May
	 * 11/11/2020
	 */
	public DBViewAllPilot() {
		try {
		statement = connection.createStatement(); 
		viewAll(); //call the viewAll function; 
		
	}catch(SQLException ex) {
		System.out.println("Database connection failed DBViewAllAircraft"); 
	}
}
	
	/**
	 * viewAll - method that calls the View_All_Pilot stored procedure in the SQL DB
	 * Kelly May
	 * 11/11/2020
	 */
	public void viewAll() {
		try {
			String method = "{call CAFDB.dbo.View_All_Pilot}"; 
			callable = connection.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * Output from View_All_Pilot:
			 * 1 = PilotID - int
			 * 2 = FirstName - varchar (string)
			 * 3 = LastName - varchar (string)
			 * 4 = DateOfBirth - date
			 * 5 = EmployeeNumber - varchar (string)
			 * 6 = DateOfHire - date 
			 * 7 = DateLeftCAF - date
			 */
			while(rs.next()) {
				//append to arraylists
				pilotID.add(rs.getInt(1)); 
				firstName.add(rs.getString(2)); 
				lastName.add(rs.getString(3)); 
				dob.add(rs.getDate(4));
				employeeNumber.add(rs.getString(5)); 
				dateOfHire.add(rs.getDate(6)); 
				dateLeftCAF.add(rs.getDate(7)); 			
								
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}
		
		
	}
	
	/**
	 * multiple get methods to access the ArrayLists individually
	 */
	public ArrayList<Integer> getPilotID(){
		return pilotID; 
	}
	
	public ArrayList<String> getFirstName(){
		return firstName; 
	}
	
	public ArrayList<String> getLastName(){
		return lastName; 
	}
	
	public ArrayList<Date> getDateOfBirth(){
		return dob; 
	}
	
	public ArrayList<String> getEmployeeNumber(){
		return employeeNumber; 
	}
	
	public ArrayList<Date> getDateOfHire(){
		return dateOfHire; 
	}
	
	public ArrayList<Date> getDateLeftCAF(){
		return dateLeftCAF; 
	}
	
	//appends first and last name into an arrayList
	public ArrayList<String> getFullName(){
		ArrayList<String> fullName = new ArrayList<>(); 
		
		for(int i =0; i<firstName.size(); i++) {
			fullName.add(firstName.get(i) + " " + lastName.get(i)); 
		}
		
		return fullName; 		
	}

	/**
	 * clearAllPilot - method to clear all the ArrayLists with pilot information
	 * Kelly May
	 * 11/11/2020
	 */
	public void clearAllPilot() {
		pilotID.clear(); 
		firstName.clear(); 
		lastName.clear(); 
		dob.clear(); 
		employeeNumber.clear(); 
		dateOfHire.clear(); 
		dateLeftCAF.clear(); 
	}


}
