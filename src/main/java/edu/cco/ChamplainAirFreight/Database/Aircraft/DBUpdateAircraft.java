package edu.cco.ChamplainAirFreight.Database.Aircraft;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 19, 2020
 * @Description: DBUpdateAircraft - class to interact with the database and the GUI page to update Aircraft
 * using the stored procedure Update_Aircraft.
 * @MODDIFIED: Novemeber 20, 2020
 * Started with strings change to int
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

public class DBUpdateAircraft extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public int modelID;
	public int aID;
	//private int idStatus;

/**
 * Database structure:
 * 1. AircraftID int
 * 2. ACModelID int
 *  
* Default Constructor
* Matt Ridgway 
* 11/19/2020
*/	
public DBUpdateAircraft() {
	//Do nothing call the class 

}//end default constructor

/**
* updateAircraft
* 
* Matt Ridgway 
* 11/19/2020
* @param aID
* @param modelID
* @param idStatus
*/	
public void updateAircraft(int aID, int modelID) {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Aircraft(?,?)}"; 
     
        callable = connection.prepareCall(storedP);
        callable.setInt(1, aID);
        callable.setInt(2,modelID);
      
       
        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Aircraft Problem!");
    }
}
/**
* Getters
* Matt Ridgway 
* 11/19/2020
*/
public int getModelID() {
	return modelID;
}

public int getaID() {
	return aID;
}

//public int getIdStatus() {
//	return idStatus;
//}

}//end class

