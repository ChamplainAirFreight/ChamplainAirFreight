package edu.cco.ChamplainAirFreight;
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
public class DBUpdateFlight extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public ArrayList<Integer> flightID=new ArrayList<>();
	public String flightInfo;
	public int aircraftID;
	public int pilotID;
	public int startAirport;
	public int endAirport;
	public Date startTime;
	public Date endTime;
	
	private int id;

/**
* Default Constructor
* Matt Ridgway 
* 11/20/2020
*/
public DBUpdateFlight() {
		
		try {
			statement=connection.createStatement();
			setFlight();
		}catch (SQLException e) {
		    System.out.println("Could Not Connect to DataBase!");
		}
}//end default constructor	
/**
* setFlight pulls flightID put's into a String with comma spacing
* Matt Ridgway 
* 11/20/2020
*/

public void setFlight() {
	
	try {
		String sql="SELECT FlightID FROM Flights";
		ResultSet result=statement.executeQuery(sql);
		while(result.next()) {
			flightInfo+=result.getString(1)+", ";
			
		}
		
	}catch (SQLException ex) {
        Logger.getLogger(DBUpdateFlight.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Could Not View Flights");
    }
	
	setFlightArray();
	
}//end setFlight
/**
* setFlightArray
* Matt Ridgway 
* 11/20/2020
* split string flightInfo by comma into list. Loop through and put into flightID List
*/	
public void setFlightArray() {
	
	List<String> flightList=new ArrayList<>(Arrays.asList(flightInfo.split(",")));
	for(String strg : flightList) {
		flightID.add(Integer.valueOf(strg));
		
	}
	
}//end setFlightArray
/**
* clearFlight
* Clear List and String
* Matt Ridgway 
* 11/20/2020
*/	
public void clearFlight() {
	
	flightID.clear();
	flightInfo="";
}//end clearFlight
/**
* setFlightSQL
* 
* Matt Ridgway 
* 11/20/2020
* @param flightSelected
*/
public void setFlightSQL(int flightSelected) {
	
	try {
	String sql="SELECT FlightID, AircraftID, PilotID, StartAirport, EndAirport,FlightStartTime,FlightEndTime "
			+ "FROM Flights Where FlightID=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, flightSelected);
    ResultSet resultS = preparedStatement.executeQuery();
	
    	while(resultS.next()) {
    		id=resultS.getInt(1);
    		aircraftID=resultS.getInt(2);
    		pilotID=resultS.getInt(3);
    		startAirport=resultS.getInt(4);
    		endAirport=resultS.getInt(5);
    		startTime=resultS.getDate(6);
    		endTime=resultS.getDate(7);
    		
    	}
	} catch (SQLException ex) {
        Logger.getLogger(DBUpdateFlight.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Get Flight Problem!");
    }
	
}//end setFlightSQL
/** RESULTSET
* updateClientAddress
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
* 
*/
public ResultSet updateFlight(int fID, int aID, int pID, int sAirport, int eAirport,Date sTime,Date eTime, int input) {
	 ResultSet results = null;
	 try {
		 String sqlState="SELECT FlightID FROM Flights WHERE FlightID=?";
		 PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement(sqlState);
      preparedStatement.setInt(1, input);
      ResultSet resultS = preparedStatement.executeQuery();
     while (resultS.next()) {
         id = resultS.getInt(1);
     }
	}catch (SQLException ex) {
    Logger.getLogger(DBUpdateFlight.class.getName()).log(Level.SEVERE, null, ex);
}
	 try {
     String SQL = "UPDATE Flights SET AircraftID = ?, "
     		+ "PilotID=?,"
     		+ "StartAirport=?,"
     		+ "EndAirport=?,"
     		+ "FlightStartTime=?,"
     		+ "FlightEndTime=?";
     	
     callable = connection.prepareCall(SQL);
     callable.setInt(2,aID);
     callable.setInt(3, pID);
     callable.setInt(4,sAirport);
     callable.setInt(5,eAirport);
     callable.setDate(6, sTime);
     callable.setDate(7, eTime);
     
     results = callable.executeQuery();

     System.out.println(results);

 } catch (SQLException ex) {
     System.out.println("Results Not Returned");
 }
 
 return results;
}//end ResultSet
/**
* updateFlight
* Matt Ridgway 
* 11/20/2020
*/
public void updateFlight() {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Flight}"; 
        PreparedStatement ps;
        ps = connection.prepareStatement(storedP);
        callable = connection.prepareCall(storedP);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Flight Problem!");
    }
}//end updateFlight
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/
public ArrayList<Integer> getFlightID() {
	return flightID;
}
public String getFlightInfo() {
	return flightInfo;
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
	return startTime;
}
public Date getEndTime() {
	return endTime;
}
public int getId() {
	return id;
}
}
