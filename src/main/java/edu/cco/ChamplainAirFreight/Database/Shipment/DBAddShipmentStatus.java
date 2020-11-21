package edu.cco.ChamplainAirFreight.Database.Shipment;
/**
 * @author Matt Ridgway
 * @Date: Nov 12, 2020
 * @Description: dbAddShipmentStatus - class to interact with the database and the GUI page 
 * to insert a new Shipment Status
 * using the stored procedure Add_Shipment_Status.
 */
// Imports:

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

import java.sql.CallableStatement;

public class DBAddShipmentStatus extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public String shipmentStatus;

/**
* Default Constructor
* Matt Ridgway 
* 11/12/2020
*/	
public DBAddShipmentStatus(String status) {
	try {
		this.shipmentStatus=status;
		
		
		String storedP = "{call CAFDB.dbo.Add_Shipment_Status}"; 
		callable = connection.prepareCall(storedP);
		insertSQL(shipmentStatus);		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddShipmentStatus.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Shipment Status"); 
	}
	
	
}
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
* 1 ShipmentStatus String
*/
public void insertSQL(String status) {
	try {
		String sql = "{call CAFDB.dbo.Add_Flight(?)}";
		callable=connection.prepareCall(sql);
		callable.setString(1,  status);
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert Shipment Status Problem !");
    }
	
	
}

}
