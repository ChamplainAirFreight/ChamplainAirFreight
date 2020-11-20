package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteAircraft - database based class that uses the DeleteAircraft stored procedure
 * to delete an aircraft from the database
 *
 */

public class DBDeleteAircraft extends DBConnection{
	
	public CallableStatement callable = null;
	private int aircraftID;
	
	public DBDeleteAircraft() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteAircraft"); 
		}
	}
	
	public void deleteAircraft(int aID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Aircraft(?)}"; 
			aircraftID = aID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, aircraftID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteAircraft.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete aircraft could not be completed"); 
		}
	}

}
