package edu.cco.ChamplainAirFreight.Database.Client;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Kelly
 * @Date Nov 13, 2020
 * @Description - class that takes an input to find a particular client row. uses View_Selected_Client in AWS DB
 * @TEST_STATUS - not tested
 */

public class DBViewSelectClient extends DBConnection {
	public CallableStatement callable = null; 
	private int clientID; 
	private String clientName; 
	private String clientType; 
	private String address1; 
	private String address2; 
	private String city; 
	private String state; 
	private String zip; 
	private String phone; 
	private int clientAddressID; 

	/**
	 * Default Constructor
	 */
	public DBViewSelectClient() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}
	
	/**
	 * viewSelected - method to view a clients information based on a ClientID
	 * @param clientID
	 */
	public void viewSelected(int cID) {
		try {
		String method = "{call CAFDB.dbo.View_Selected_Client(?)}"; 
		callable = connection.prepareCall(method); 
		callable.setInt(1, cID); // call the client ID for searching
		
		//execute the query
		ResultSet rs = callable.executeQuery(); 
		
		/**
		 * output from View_Selected_Client
		 * 1 = ClientID
		 * 2 = ClientName
		 * 3 = Client Type ID
		 * 4 = phone number
		 * 5 = client address id
		 * 6 = client ID
		 * 7 = address line 1
		 * 8 = address line 2
		 * 9 = city
		 * 10 = state
		 * 11 = zip
		 */
		while(rs.next()) {
			clientID = rs.getInt(1); 
			clientName = rs.getString(2); 
			clientType = rs.getString(3); 
			phone = rs.getString(4); 
			address1 = rs.getString(7); 
			address2 = rs.getString(8); 
			city = rs.getString(9);
			state = rs.getString(10); 
			zip = rs.getString(11); 
			clientAddressID = rs.getInt(5); 
		}
		
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}		
		
	}
	
	/**
	 * multiple getter methods to grab each variable independently 
	 */
	public int getClientID() {
		return clientID;
	}
	public String getClientName() {
		return clientName; 
	}
	public String getClientType() {
		return clientType;
	}
	public String getAddress1() {
		return address1; 
	}
	public String getAddress2() {
		return address2; 
	}
	public String getCity() {
		return city; 
	}
	public String getState() {
		return state; 
	}
	public String getZip() {
		return zip; 
	}
	public String getPhone() {
		return phone; 
	}
	public int getAddressID() {
		return clientAddressID; 
	}
	
	public void clearAll() {
		clientID = 0; 
		clientName = ""; 
		clientType = ""; 
		address1 =""; 
		address2 =""; 
		city = ""; 
		state = ""; 
		zip = ""; 
		phone = ""; 				
	}
	
} //end class 
