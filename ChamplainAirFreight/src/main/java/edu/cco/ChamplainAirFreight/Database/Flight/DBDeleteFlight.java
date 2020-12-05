package edu.cco.ChamplainAirFreight.Database.Flight;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteFlight - database based class that uses the DeleteFlight stored procedure
 * to delete a flight from the database
 *
 */

public class DBDeleteFlight extends DBConnection{
	
	public CallableStatement callable = null;
	private int flightID;
	
	public DBDeleteFlight() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteFlight"); 
		}
	}
	
	public void deleteFlight(int fID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Flight(?)}"; 
			flightID = fID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, flightID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			//wont return resultset -- Logger.getLogger(DBDeleteFlight.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete flight could not be completed"); 
		}
	}

}
