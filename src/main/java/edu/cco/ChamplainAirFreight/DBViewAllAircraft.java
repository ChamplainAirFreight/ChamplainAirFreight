package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Kelly
 * @Date Nov 11, 2020
 * @Description - class that pulls a DBConnection in order to view all the aircraft information, using the View_All_Aircraft 
 * stored procedure
 * TESTED STATUS - not tested
 */

public class DBViewAllAircraft extends DBConnection {
	//variables
	public CallableStatement callable = null; 
	private ArrayList<Integer> acID = new ArrayList<>();
	private ArrayList<Integer> acModelID = new ArrayList<>(); 
	private ArrayList<Integer> acStatusID = new ArrayList<>(); 
	private ArrayList<String> acMake = new ArrayList<>();
	private ArrayList<String> acModel = new ArrayList<>(); 
	private ArrayList<String> acRange = new ArrayList<>(); 
	private ArrayList<String> acRangeClass = new ArrayList<>(); 
	private ArrayList<Double> acPayload = new ArrayList<>(); 
	private ArrayList<Double> acLoadVolume = new ArrayList<>(); 	
	
	/**
	 * Default Constructor
	 * Kelly May
	 * 11/11/2020
	 */
	public DBViewAllAircraft() {
		try {
			statement = connection.createStatement(); 
			viewAll(); //call the viewAll function; 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}

	/**
	 * viewAll - method that calls the View_All_Aircraft stored procedure in the SQL db
	 * Kelly May
	 * 11/11/2020
	 */
	public void viewAll() {
		try {
			String method = "{call CAFDB.dbo.View_All_Aircraft}"; 
			callable = connection.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * Output from View_All_Aircraft:
			 * 1 = AircraftID - int
			 * 2 = ACModelID - int
			 * 3 = AircraftStatusID - int
			 * 4 = ACModelID - int --- this shows up twice so only add once
			 * 5 = ACMake - Varchar (string)
			 * 6 = ACModel - varchar (string)
			 * 7 = ACRange - varchar (string)
			 * 8 = ACRangeClassification - varchar (string)
			 * 9 = ACPayload - float (int or double?)
			 * 10 = ACLoadVolume - float (int or double?)
			 */
			while(rs.next()) {
				acID.add(rs.getInt(1)); 
				acModelID.add(rs.getInt(2)); 
				acStatusID.add(rs.getInt(3)); 
				acMake.add(rs.getString(5)); 
				acModel.add(rs.getString(6)); 
				acRange.add(rs.getString(7)); 
				acRangeClass.add(rs.getString(8)); 
				acPayload.add(rs.getDouble(9)); 
				acLoadVolume.add(rs.getDouble(10)); 				
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}
	}
	
	/**
	 * multiple get methods to access each ArrayList individually
	 */
	public ArrayList<Integer> getAircraftID(){
		return acID; 
	}
	
	public ArrayList<Integer> getAircraftModelID(){
		return acModelID; 
	}
	
	public ArrayList<Integer> getAircraftStatusID(){
		return acStatusID; 
	}
	
	public ArrayList<String> getAircraftMake(){
		return acMake; 
	}
	
	public ArrayList<String> getAircraftModel(){
		return acModel; 
	}
	
	public ArrayList<String> getAircraftRange(){
		return acRange; 
	}
	
	public ArrayList<String> getAircraftRangeClass(){
		return acRangeClass; 
	}
	
	public ArrayList<Double> getAircraftPayload(){
		return acPayload; 
	}
	
	public ArrayList<Double> getAircraftLoadVolume(){
		return acLoadVolume; 
	}
	
	/**
	 * clearAllAircraft - method that clears the arraylists with aircraft information 
	 * Kelly May
	 * 11/11/2020
	 */
	public void clearAllAircraft() {
		acID.clear(); 
		acModelID.clear(); 
		acStatusID.clear(); 
		acMake.clear(); 
		acModel.clear(); 
		acRange.clear(); 
		acRangeClass.clear(); 
		acPayload.clear(); 
		acLoadVolume.clear(); 
	}
} //end class DBViewAllAircraft
