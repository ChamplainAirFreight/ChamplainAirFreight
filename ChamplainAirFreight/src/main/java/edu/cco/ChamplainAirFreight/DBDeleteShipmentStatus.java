package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteShipmentStatus - database based class that uses the DeleteShipmentStatus stored procedure
 * to delete a shipment status from the database
 *
 */

public class DBDeleteShipmentStatus extends DBConnection{
	
	public CallableStatement callable = null;
	private int shipmentStatusID;
	
	public DBDeleteShipmentStatus() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteShipmentStatus"); 
		}
	}
	
	public void deleteShipmentStatus(int ssID) {
		try {
			String method = "{call CAFDB.dbo.Delete_Shipment_Status(?)}"; 
			shipmentStatusID = ssID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, shipmentStatusID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteShipmentStatus.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete shipment status could not be completed"); 
		}
	}

}
