package edu.cco.ChamplainAirFreight.Database.Flight;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 12, 2020
 * @Description: dbAddFlight - class to interact with the database and the GUI page to insert a new Flight
 * using the stored procedure Add_Flight.
 * 
 * Not tested will need to see what data type smalldatetime is in Java
 */

// Imports:
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

public class DBAddFlight extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public int aircraftID;
		public int pilotID;
		public int startAirportID;
		public int endAirportID;
		public Date start;
		public Date end;

/**
* Default Constructor
* Matt Ridgway 
* 11/12/2020
*/		
public DBAddFlight(int aID,int pID,int startAirport, int endAirport, Date start, Date end ) {
	try {
		this.aircraftID=aID;
		this.pilotID=pID;
		this.startAirportID=startAirport;
		this.endAirportID=endAirport;
		this.start=start;
		this.end=end;
		
		//String storedP = "{call CAFDB.dbo.Add_Flight}"; 
		//callable = connection.prepareCall(storedP);
		statement = connection.createStatement(); 
		insertSQL(aircraftID,pilotID,startAirportID,endAirportID, start,end);		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddFlight.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Client"); 
	}
	
}//end constructor

/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
 * 1 AircraftID int
 * 2 PilotID int
 * 3 StartAirport int
 * 4 EndAirport int
 * 5 FlightStartTime smalldatetime
 * 6 FlightEndTime smalldatetime
* 
*
*/
public void insertSQL(int aID,int pID,int startAirport, int endAirport, Date start, Date end ) {
	try {
		String sql = "{call CAFDB.dbo.Add_Flight(?,?,?,?,?,?)}";
		callable=connection.prepareCall(sql);
		callable.setInt(1,  aID);
		callable.setInt(2,  pID);
		callable.setInt(3,  startAirport);
		callable.setInt(4,  endAirport);
		callable.setDate(5, start);
		callable.setDate(6, end);
			
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert Flight Problem !");
    }
	
	
}
}
