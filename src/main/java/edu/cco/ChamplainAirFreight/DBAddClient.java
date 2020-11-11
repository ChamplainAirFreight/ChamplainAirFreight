package edu.cco.ChamplainAirFreight;

/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: dbAddClient - class to interact with the database and the GUI page to insert a new client
 * using the stored procedure Add_Client.
 */

// Imports:
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Database structure:
 * 1 ClientID int
 * 2 ClientName string
 * 3 ClientTypeID int
 * 4 ClientPhoneNumber string
 *
 */
public class DBAddClient extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public String name;
	public int type;
	public String phone;
	

	
/**
* Default Constructor
* Matt Ridgway 
* 11/11/2020
*/
public DBAddClient() {
	
	try {
		statement=connection.createStatement();
		insertClient(name, type, phone);
	}
	catch(SQLException ex){
		System.out.println("Database connection failed for DBAddClient !");
		
	}
}//End Default Constructor

/**
* insertClient Method calls the stored procedure Add_Client in the SQL database
* Matt Ridgway 
* 11/11/2020
*/
public void insertClient(String clientName, int clientType, String clientPhone) {
	try {
		String storedP = "{call CAFDB.dbo.Add_Client}"; 
		callable = connection.prepareCall(storedP);
		String sql= "INSERT INTO Clients VALUES (clientName,clientType, clientPhone)";
		statement.addBatch(sql);
		
		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddClient.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Client"); 
	}
	
}
	
}
