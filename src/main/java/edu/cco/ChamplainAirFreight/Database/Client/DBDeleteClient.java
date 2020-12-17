package edu.cco.ChamplainAirFreight.Database.Client;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteClient - database based class that uses the DelentClient stored procedure
 * to delete a client from the database
 *
 */
public class DBDeleteClient extends DBConnection{
	
	public CallableStatement callable = null;
	private int clientID;
	
	public DBDeleteClient() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteClient"); 
		}
	}
	
	public void deleteClient(int cID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Client(?)}"; 
			clientID = cID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, clientID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete client could not be completed"); 
		}
	}
	
}
