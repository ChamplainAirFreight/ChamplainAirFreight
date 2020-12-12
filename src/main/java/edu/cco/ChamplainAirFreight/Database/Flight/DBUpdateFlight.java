package edu.cco.ChamplainAirFreight.Database.Flight;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 20, 2020
 * @Description: DBUpdateFlight - class to interact with the database and the GUI page 
 * to update the Flight information, using the stored procedure Update_Flight.
 * @MODDIFIED: 
 */
//Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;
public class DBUpdateFlight extends DBConnection {
//Variables
public CallableStatement callable = null;
public int aID;
public int pID;
public int sAirport;
public int eAirport;
public String sTime;
public String eTime;
//public String returnString;


/**
* DataBase Structure:
* 1. FlightId int
* 2. AircraftID int
* 3. PilotID int
* 4. StartAirport int
* 5. EndAirport int
* 6. FlightStartTime Date USED STRING correctly formatted
* 7. FlightEndTime Date USED STRING correctly formatted
* 
* Default Constructor
* Matt Ridgway 
* 11/20/2020
*/
public DBUpdateFlight() {
	
	//blank
}//end default constructor	

/** 
* updateFlight
* 
* Matt Ridgway 
* 11/20/2020
* @param fID
* @param aID
* @param pID
* @param sAirport
* @param eAirport
* @param sTime
* @param eTime
*/
public void updateFlight(int fID, int aID, int pID, int sAirport, int eAirport,String sTime,String eTime) {
try {
	String storedP = "{call CAFDB.dbo.Update_Flight(?,?,?,?,?,?)}"; 
  
    callable = connection.prepareCall(storedP);
    callable.setInt(2, fID);
    callable.setInt(2,aID);
    callable.setInt(3, pID);
    callable.setInt(4,sAirport);
    callable.setInt(5,eAirport);
    callable.setString(6, sTime);
    callable.setString(7, eTime);
    ResultSet rs = callable.executeQuery(); 
    //returnString=rs.toString();

} catch (SQLException ex) {
    System.out.println("Update Flight Problem!");
}
}//end updateFlight
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/

public int getaID() {
	return aID;
}

public int getpID() {
	return pID;
}

public int getsAirport() {
	return sAirport;
}

public int geteAirport() {
	return eAirport;
}

public String getsTime() {
	return sTime;
}

public String geteTime() {
	return eTime;
}

//public String returnString() {
//	return returnString;
//}
}
