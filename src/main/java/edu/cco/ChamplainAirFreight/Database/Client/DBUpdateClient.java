package edu.cco.ChamplainAirFreight.Database.Client;

/**
 * Working
 * @author Matt Ridgway
 * @Date: November 20, 2020
 * @Description: DBUpdateClient - class to interact with the database and the GUI page 
 * to update the Client information, using the stored procedure Update_Client.
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

public class DBUpdateClient extends DBConnection{
	//Variables
			public CallableStatement callable = null;
			public String cName;
			public int cType;
			public int cID;
			public String cPhone;
		
/**
 * Database structure:
 * 1. Client ID
 * 2. ClientName String
 * 3. ClientTypeID int
 * 4. ClientPhoneNumber String
* Matt Ridgway 
* 11/20/2020
/** Blank
* Default Constructor
*/
public DBUpdateClient() {
	//Do nothing call the class 
}		

/** 
* updateClient
* 
* Matt Ridgway 
* 11/20/2020
* @param cID
* @param cName
* @param cIDtype
* @param cPhone
*
*/
public void updateC(int cID, String cName, int cType, String cPhone) {
    
	try {
    	String storedP = "{call CAFDB.dbo.Update_Client(?,?,?,?)}"; 

        callable = connection.prepareCall(storedP);

        callable.setInt(1, cID);
        callable.setString(2, cName);
        callable.setInt(3, cType);
        callable.setString(4, cPhone);
        //callable.executeQuery(); 
        ResultSet rs = callable.executeQuery(); 
    } catch (SQLException ex) {
        System.out.println("Update Client Problem!");
    }
	
	
}//end updateClient

/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/

public String getcName() {
	return cName;
}

public int getcType() {
	return cType;
}

public int getcID() {
	return cID;
}

public String getcPhone() {
	return cPhone;
}


}//end class
