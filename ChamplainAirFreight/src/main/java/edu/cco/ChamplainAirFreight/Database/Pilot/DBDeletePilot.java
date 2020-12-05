package edu.cco.ChamplainAirFreight.Database.Pilot;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeletePilot - database based class that uses the DeletePilot stored procedure
 * to delete a pilot from the database
 *
 */

public class DBDeletePilot extends DBConnection{
	
	public CallableStatement callable = null;
	private int pilotID;
	
	public DBDeletePilot() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeletePilot"); 
		}
	}
	
	public void deletePilot(int pID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Pilot(?)}"; 
			pilotID = pID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, pilotID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			//- will not return a result set -- Logger.getLogger(DBDeletePilot.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete pilot could not be completed"); 
		}
	}

}
