package edu.cco.ChamplainAirFreight.Database.Aircraft;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteAircraftModel - database based class that uses the DeleteAircraftModel stored procedure
 * to delete an aircraft model from the database
 *
 */

public class DBDeleteAircraftModel extends DBConnection{
	
	public CallableStatement callable = null;
	private int aircraftModelID;
	
	public DBDeleteAircraftModel() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteAircraftModel"); 
		}
	}
	
	public void deleteAircraftModel(int amID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Aircraft_Model(?)}"; 
			aircraftModelID = amID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, aircraftModelID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteAircraftModel.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete aircraft model could not be completed"); 
		}
	}
	}