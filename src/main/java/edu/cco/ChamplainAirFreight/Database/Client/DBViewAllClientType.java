package edu.cco.ChamplainAirFreight.Database.Client;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan
 * @Date Dec 11, 2020
 * @Description - class that relates client type to the client type id. uses View_All_Client_Type in AWS DB
 * @TEST_STATUS - not tested
 */

public class DBViewAllClientType extends DBConnection {
	
	public CallableStatement callable = null; 
	private ArrayList<Integer> clientTypeId;
	private ArrayList<String> clientType;
	
	public DBViewAllClientType() {
		try {
			statement = connection.createStatement(); 
			viewAll();
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllClientType"); 
		}
		
	}
	
	public void viewAll() {
		try {
			String method = "{call CAFDB.dbo.View_All_Client_Type}"; 
			callable = connection.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery();
			/**
			 * 1 = ClientTypeID
			 * 2 = ClientType
			 */
			
			while(rs.next()) {
				clientTypeId.add(rs.getInt(1)); 
				clientType.add(rs.getString(2)); 			 
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all client types could not be completed"); 
		}
	}

	//use just to print all strings to test - this will show in CLI. 
	public void getResults() {
		for(int i = 0; i<clientType.size(); i++) {
			System.out.println(clientTypeId.get(i)+ "-" + clientType.get(i)); 
		}
	}

	public ArrayList<Integer> getID(){
		return clientTypeId; 
	}
	
	public ArrayList<String> getClientType(){
		return clientType; 
	}	

	/**
	 * clearAllClientTypess -clears the arraylists of all the client information. 
	 */
	public void clearAllClientTypes() {
		clientTypeId.clear(); 
		clientType.clear(); 
	}

}
