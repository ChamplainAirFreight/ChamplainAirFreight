package edu.cco.ChamplainAirFreight.Database.Client;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 20, 2020
 * @Description: DBUpdateClientAddress - class to interact with the database and the GUI page 
 * to update the Client address information, using the stored procedure Update_Client_Address.
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
public class DBUpdateClientAddress  extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public String clientAdd1;
	public String clientAdd2;
	public String clientCity;
	public String clientState;
	public String clientInfo;
	public int clientZip;


/**
 * DataBase structure:
 * 1. ClientAddressID int
 * 2. ClientID int
 * 3. ClientAddressLine1 String
 * 4. ClientAddressLine2 String
 * 5. ClientAddressCity String
 * 6. ClientAddressState String
 * 7. ClientAddressZip int
* Default Constructor
* Blank
 */
	public DBUpdateClientAddress() {
		//Just call class do nothing
	}		


/** 
* updateClientAddress
* 
* Matt Ridgway 
* 11/20/2020
* @param cID
* @param add1
* @param add2
* @param city
* @param state
* @param zip
* updateClientAddress
* Matt Ridgway 
* 11/20/2020
*/
public void updateClientA(int cID, String add1, String add2,String city,String state,int zip) {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Client_Address(?,?,?,?,?,?)}"; 
     
        callable = connection.prepareCall(storedP);
   
          callable.setInt(2, cID);
          callable.setString(3, add1);
          callable.setString(4, add2);
          callable.setString(5, city);
          callable.setString(6, state);
          callable.setInt(7, zip);


          ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Client Address Problem!");
    }
}//end updateClientAddress
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/
//public ArrayList<Integer> getClientID() {
//	return clientID;
//}
public String getClientAdd1() {
	return clientAdd1;
}
public String getClientAdd2() {
	return clientAdd2;
}
public String getClientCity() {
	return clientCity;
}
public String getClientState() {
	return clientState;
}
public String getClientInfo() {
	return clientInfo;
}
public int getClientZip() {
	return clientZip;
}
//public int getId() {
//	return id;
//}
}//end class
