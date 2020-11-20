package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteAirport - database based class that uses the DeleteAirport stored procedure
 * to delete an airport from the database
 *
 */

public class DBDeleteAirport extends DBConnection{
	
	public CallableStatement callable = null;
	private int airportID;
	
	public DBDeleteAirport() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteAirport"); 
		}
	}
	
	public void deleteAirport(int aID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Airport(?)}"; 
			airportID = aID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, airportID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteAirport.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete airport could not be completed"); 
		}
	}

}
