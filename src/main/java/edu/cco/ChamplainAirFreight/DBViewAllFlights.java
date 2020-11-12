package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;

/**
 * 
 * @author Kelly
 *@Date Nov 10, 2020
 *@Description - class that calls on the AWS database, and uses the View_All_Flights stored procedure
 *to fill arraylists with flight information.
 *TESTED STATUS - confirmed
 */
public class DBViewAllFlights extends DBConnection{
	//global variables
	private CallableStatement callable = null; 
	private ArrayList<Integer> id = new ArrayList<>(); //flightID
	private ArrayList<Integer> aircraftID = new ArrayList<>(); //aircraftID
	private ArrayList<Integer> pilotID = new ArrayList<>(); 
	private ArrayList<Integer> start = new ArrayList<>(); //StartAirport
	private ArrayList<Integer> end = new ArrayList<>(); //EndAirport
	private ArrayList<Time> startTime = new ArrayList<>(); //start time
	private ArrayList<Time> endTime = new ArrayList<>(); // end time
	
	
	/**
	 * default constructor - connection to db
	 */ 
	public DBViewAllFlights() {
		try {
		statement = connection.createStatement(); 
		viewAll(); // call the viewAll function. 
		
	} catch(SQLException ex) {
		System.out.println("Database connection failed DBViewAllFlights"); 
	}}
	
	
	/**
	 * viewAll- method that calls the View_All_Flights stored procedure to fill ArrayLists. 
	 */
	public void viewAll() {
		try {
			String method ="{call CAFDB.dbo.View_All_Flight}"; 
			callable = connection.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			/**
			 * 1 = flightID - int
			 * 2 = AircraftID - int
			 * 3 = PilotID - int
			 * 4 = StartAirport - int
			 * 5 = EndAirport - int
			 * 6 = FlightStartTime - smalldatetime
			 * 7 = FlightEndTime - smalldatetime
			 *** there are more that print out from this SP. 
			 */
			
			while(rs.next()) {
				//fill arraylists
				id.add(rs.getInt(1)); 
				aircraftID.add(rs.getInt(2)); 
				pilotID.add(rs.getInt(3)); 
				start.add(rs.getInt(4)); 
				end.add(rs.getInt(5)); 
				startTime.add(rs.getTime(6)); 
				endTime.add(rs.getTime(7)); 				
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all flights could not be completed"); 
		}
		
	}
	
	public ArrayList<Integer> getFlightID(){
		return id; 
	}
	
	public ArrayList<Integer> getAircraftID(){
		return aircraftID; 
	}
	public ArrayList<Integer> getPilotID(){
		return pilotID; 
	}
	public ArrayList<Integer> getStartLocation(){
		return start; 
	}
	public ArrayList<Integer> getEndLocation(){
		return end; 
	}
	public ArrayList<Time> getStartTime(){
		return startTime; 
	}
	public ArrayList<Time> getEndTime(){
		return endTime; 
	}
	
	/**
	 * clearFlights - clears all the ArrayLists with flight information 
	 * Kelly May
	 * 11/11/2020
	 */
	public void clearFlights() {
		id.clear(); 
		aircraftID.clear(); 
		pilotID.clear(); 
		start.clear(); 
		end.clear(); 
		startTime.clear(); 
		endTime.clear(); 
	}

}
