package edu.cco.ChamplainAirFreight.Database.Client;

/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 12, 2020
 * @Description: dbAddClientAddress - class to interact with the database and the GUI page to insert a new client address
 * using the stored procedure Add_Client_Address.
 */

// Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

public class DBAddClientAddress extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public String clientAdd1;
	public String clientAdd2;
	public String clientCity;
	public String clientState;
	public int clientZip;
	public int clientID;
	
/**
* Default Constructor
* Matt Ridgway 
* 11/12/2020
*/
public DBAddClientAddress(String add1,String add2,String city,String state, int zip, int id) {
	try {
		this.clientAdd1=add1;
		this.clientAdd2=add2;
		this.clientCity=city;
		this.clientState=state;
		this.clientZip=zip;
		this.clientID=id;
		
		statement = connection.createStatement(); 
		insertSQL(clientAdd1,clientAdd2,clientCity,clientState,clientZip,clientID);		
		
	}catch (SQLException ex) {
		Logger.getLogger(DBAddClientAddress.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Client Address"); 
	}	
	
}//end constructor

	
	/**
	 * Default Constructor
	 */
	public DBAddClientAddress() {
		//just call this class
	}

/**
* insertSQL Method 
* Matt Ridgway 
* 11/12/2020
* Database structure:
* 1 ClientID int
* 2 ClientAddress1 string
* 3 ClientAddress2 string
* 4 ClientCity string
* 5 ClientState string
* 6 ClientZip int
*
*/
public void insertSQL(String add1,String add2,String city,String state, int zip, int id) {
	try {
		String sql = "{call CAFDB.dbo.Add_Client_Address(?,?,?,?,?,?)}";
		callable=connection.prepareCall(sql);
		callable.setInt(1,  id);
		callable.setString(2, add1);
		callable.setString(3, add2);
		callable.setString(4, city);
		callable.setString(5, state);
		callable.setInt(6, zip);
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
		//uncomment logger/printstacktrace for debugging. 
		//Logger.getLogger(DBAddClientAddress.class.getName()).log(Level.SEVERE, null, ex);
		//ex.printStackTrace(); 
        System.out.println("Insert Client Address Problem !");
    }
	
	
}

}
