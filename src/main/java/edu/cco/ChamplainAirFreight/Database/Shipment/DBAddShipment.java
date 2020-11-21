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
			public int shipmentID;
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
public DBAddShipment(int sID, int cID, float vol, float weight, int stat, Date start, Date end, String notes) {
	try {
		this.shipmentID=sID;
		this.clientID=cID;
		this.volume=vol;
		this.weight=weight;
		this.statusID=stat;
		this.start=start;
		this.end=end;
		this.notes=notes;
		
		String storedP = "{call CAFDB.dbo.Add_Shipment}"; 
		callable = connection.prepareCall(storedP);
		insertSQL(shipmentID,clientID,volume,weight,statusID,start,end,notes);		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddShipment.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Shipment"); 
	}
	
	
}//end constructor
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
* 1 ShipmentId int
* 2 ClientID int
* 3 ShipmentVolume float
* 4 ShipmentWieght float
* 5 ShipmentStatusID int
* 6 ShipmentStartDate Date
* 7 ShipmentEndDate Date
* 8 ShipmentNotes String
*
*/
public void insertSQL(int sID, int cID, float vol, float weight, int stat, Date start, Date end, String notes) {
	try {
		String sql = "{call CAFDB.dbo.Add_Flight(?,?,?,?,?,?,?,?)}";
		callable=connection.prepareCall(sql);
		callable.setInt(1,  sID);
		callable.setInt(2,  cID);
		callable.setFloat(3,  vol);
		callable.setFloat(4,  weight);
		callable.setInt(5, stat);
		callable.setDate(6, start);
		callable.setDate(7, end);
		callable.setString(8, notes);
			
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert Flight Problem !");
    }
	
	
}


}
