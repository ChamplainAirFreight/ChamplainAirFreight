package edu.cco.ChamplainAirFreight.Database.Flight;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 20, 2020
 * @Description: DBUpdateAirport - class to interact with the database and the GUI page 
 * to update the airport, using the stored procedure Update_Airport.
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
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;
public class DBUpdateAirport extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public String aName;
		public String aLoc;
		public boolean aHub;
		public float aDis;
		public int aID;
		

/**
 * Database Structure:
 * 1. AirportID int
 * 2. AirportName String
 * 3. AirportLocation String
 * 4. AirportHub Boolean
 * 5. AirportDistanceFromHub float
* Default Constructor
* Matt Ridgway 
* 11/20/2020
*/	
public DBUpdateAirport() {
			
		//blank
}//end default constructor

/**
* updateAirport
* 
* Matt Ridgway 
* 11/20/2020
* @param aID
* @param aName
* @param aLoc
* @param aHub
* @param aDist
* @param input
*/
public void updateAirport(int aID, String aName, String aLoc, boolean aHub, float aDist) {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Airport(?,?,?,?,?)}"; 
       
        callable = connection.prepareCall(storedP);
        callable.setInt(1, aID);
        callable.setString(2, aName);
        callable.setString(3, aLoc);
        callable.setBoolean(4, aHub);
        callable.setFloat(5, aDist);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Airport Problem!");
    }
}//end updateAirport
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/

public String getaName() {
	return aName;
}

public String getaLoc() {
	return aLoc;
}

public boolean isaHub() {
	return aHub;
}

public float getaDis() {
	return aDis;
}

public int getaID() {
	return aID;
}


}
