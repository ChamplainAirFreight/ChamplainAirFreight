package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		 * 5 = address id
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
			address1 = rs.getString(7); 
			address2 = rs.getString(8); 
			city = rs.getString(9);
			state = rs.getString(10); 
			zip = rs.getString(11); 
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
	
} //end class 
