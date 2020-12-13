package edu.cco.ChamplainAirFreight.Database.Client;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan
 * @Date Dec 11, 2020
 * @Description - class that relates client type to the client type id. uses View_All_Client_Type_By_Name in AWS DB
 * @TEST_STATUS - not tested
 */

public class DBViewSelectedClientTypeByName extends DBConnection {
	public CallableStatement callable = null; 
	private int clientTypeId;
	private String clientType;
	
	/**
	 * Default Constructor
	 */
	public DBViewSelectedClientTypeByName() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewSelectClientTypeByName"); 
		}
		
	}
	
	/**
	 * viewSelected - method to view a clients information based on a ClientID
	 * @param clientTypeID
	 */
	public void viewSelected(String cT) {
		try {
		String method = "{call CAFDB.dbo.View_Selected_Client_Type_By_Name(?)}"; 
		callable = connection.prepareCall(method); 
		callable.setString(1, cT); // call the client ID for searching
		
		//execute the query
		ResultSet rs = callable.executeQuery(); 
		
		/**
		 * output from View_Selected_Client_Type
		 * 1 = Client Type ID
		 * 2 = Client Type		 
		 */
		while(rs.next()) {
			clientTypeId = rs.getInt(1); 
			clientType = rs.getString(2);			 
		}
		rs.close(); 
		
		}catch (SQLException ex) {
			//Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			//e.printStackTrace();
			System.out.println("view selected type by name could not be completed"); 
		}		
		
	}
	
	public int getID(){
		return clientTypeId; 
	}
	
	public String getClientType(){
		return clientType; 
	}	
}
