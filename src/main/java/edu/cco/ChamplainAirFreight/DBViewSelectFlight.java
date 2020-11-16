package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Kelly
 * @Date Nov 16, 2020
 * @Description - calss that takes an input to find a particular flight row. uses the View_Select_Flight
 *  Stored procedure in AWS DB
 * @TEST_STATUS - not tested 
 */

public class DBViewSelectFlight extends DBConnection {
	//variables
	public CallableStatement callable = null; 
	private int flightID; 
	private int aircraftID; 
	private int pilotID; 
	private int startAirport; 
	private int endAirport; 
	private Date flightStartTime; 
	private Date flightEndTime; 
	
/**
 * Default Constructor
 */
	public DBViewSelectFlight() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}
	
	/**
	 * viewSelected - method to view a flight based on the Flight ID
	 */
	public void viewSelected(int fID) {
		try {
			String method = "{call CAFDB.dbo.View_Selected_Flight(?)}"; 
			callable = connection.prepareCall(method); 
			callable.setInt(1, fID); // call the flight ID for searching
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * output from View_Selected_Flight
			 * 1 = flightID - int
			 * 2 = AircraftID - int
			 * 3 = PilotID - int
			 * 4 = StartAirport - int
			 * 5 = EndAirport - int
			 * 6 = FlightStartTime - smalldatetime
			 * 7 = FlightEndTime - smalldatetime
			 * * There are more that print out from the SP, but focusing on top 7
			 */
			while(rs.next()) {
				flightID = rs.getInt(1); 
				aircraftID = rs.getInt(2); 
				pilotID = rs.getInt(3); 
				startAirport = rs.getInt(4); 
				endAirport = rs.getInt(5); 
				flightStartTime = rs.getDate(6);
				flightEndTime = rs.getDate(7); 
				 
			}
			
			}catch (SQLException ex) {
				Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("view all clients could not be completed"); 
			}	
	}
	
	/**
	 * multiple getter methods to get each variable independently. 
	 */
	public int getFlightID() {
		return flightID; 
	}
	public int getAircraftID() {
		return aircraftID; 
	}
	public int getPilotID() {
		return pilotID; 
	}
	public int getStartAirport() {
		return startAirport; 
	}
	public int getEndAirport() {
		return endAirport; 
	}
	public Date getStartTime() {
		return flightStartTime; 
	}
	public Date getEndTime() {
		return flightEndTime; 
	}
}
