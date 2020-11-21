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
 * @Description: DBDeleteClientAddress - database based class that uses the DeleteClientAddress stored procedure
 * to delete an client address from the database
 *
 */

public class DBDeleteClientAddress extends DBConnection{
	
	public CallableStatement callable = null;
	private int clientAddressID;
	
	public DBDeleteClientAddress() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteClientAddress"); 
		}
	}
	
	public void deleteClientAddress(int caID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Client_Address(?)}"; 
			clientAddressID = caID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, clientAddressID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteClientAddress.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete client address could not be completed"); 
		}
	}

}
