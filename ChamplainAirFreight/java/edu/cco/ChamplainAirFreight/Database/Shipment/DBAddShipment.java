package edu.cco.ChamplainAirFreight.Database.Shipment;
/**@author Matt Ridgway
 * @Date: Nov 12, 2020
 * @Description: dbAddShipment - class to interact with the database and the GUI page to insert a new Shipment
 * using the stored procedure Add_Shipment.
 */

// Imports:
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

public class DBAddShipment extends DBConnection{
	//Variables
			public CallableStatement callable = null;
			// shipment ID not in stored procedure -- public int shipmentID; 
			public int clientID;
			public float volume;
			public float weight;
			public int statusID;
			public Date start;
			public Date end;
			public String notes;
/**
* Default Constructor
* Matt Ridgway 
* 11/12/2020
*/	
public DBAddShipment() {
	//just initialize the class
	}
	
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
* 2 ClientID int
* 3 ShipmentVolume float
* 4 ShipmentWieght float
* 5 ShipmentStatusID int
* 6 ShipmentStartDate Date
* 7 ShipmentEndDate Date
* 8 ShipmentNotes String
*
*/
public void insertSQL(int cID, float vol, float weight, int stat, Date start, Date end, String notes) {
	try {
		String sql = "{call CAFDB.dbo.Add_Shipment(?,?,?,?,?,?,?)}";
		callable=connection.prepareCall(sql);
		callable.setInt(1,  cID);
		callable.setFloat(2,  vol);
		callable.setFloat(3,  weight);
		callable.setInt(4, stat);
		callable.setDate(5, start);
		callable.setDate(6, end);
		callable.setString(7, notes);
			
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert Shipment Problem !");
    }
	
	
}


}
