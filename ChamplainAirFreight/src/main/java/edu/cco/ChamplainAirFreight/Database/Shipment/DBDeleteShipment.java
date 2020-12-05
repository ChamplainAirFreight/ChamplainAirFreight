package edu.cco.ChamplainAirFreight.Database.Shipment;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteShipment - database based class that uses the DeleteShipment stored procedure
 * to delete a shipment from the database
 *
 */

public class DBDeleteShipment extends DBConnection{
	
	public CallableStatement callable = null;
	private int shipmentID;
	
	public DBDeleteShipment() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteShipment"); 
		}
	}
	
	public void deleteShipment(int sID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Shipment(?)}"; 
			shipmentID = sID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, shipmentID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			//Logger.getLogger(DBDeleteShipment.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			//e.printStackTrace();
			System.out.println("delete shipment could not be completed"); 
		}
	}
}
