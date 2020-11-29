package edu.cco.ChamplainAirFreight.Database.Client;

/**
 * NOT TESTED
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
			public String clientName;
			public int clientIDType;
			public String clientPhone;
		

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

/** RESULTSET
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
public void updateC(int cID, String cName, int cIDType, String cPhone) {
    
	try {
    	String storedP = "{call CAFDB.dbo.Update_Client(?,?,?,?)}"; 

        callable = connection.prepareCall(storedP);

        callable.setInt(1, cID);
        callable.setString(2, cName);
        callable.setInt(3, cIDType);
        callable.setString(4, cPhone);
        //callable.executeQuery(); 
        ResultSet rs = callable.executeQuery(); 
    } catch (SQLException ex) {
        System.out.println("Update Client Problem!");
    }
	
	
}//end updateClient

/**
 * exampleQuery
 * example for Matt to follow
 */
public void exampleQuery(int cID, String cName, int type, String phone) {
	try {
	String storedP = "{call CAFDB.dbo.Update_Client(?,?,?,?)}";
	//set your query/storedP
	callable = connection.prepareCall(storedP); 
	//set your variables for the storedP
	callable.setInt(1,  cID);
	callable.setString(2, cName);
	callable.setInt(3, type);
	callable.setString(4, phone);
	//execute the storedP
	ResultSet rs = callable.executeQuery(); 
	//this should be all you need. You will fill pass the variables from the GUI entries
}catch(SQLException ex) {
	//ex.printStackTrace(); 
	//prinstacktrace used for debugging 
}
	}
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/

//public ArrayList<Integer> getClientID() {
//	return clientID;
//}
//public String getClientInfo() {
//	return clientInfo;
//}
public String getClientName() {
	return clientName;
}
public int getClientIDType() {
	return clientIDType;
}
public String getClientPhone() {
	return clientPhone;
}
//public int getId() {
//	return id;
//}
}//end class
