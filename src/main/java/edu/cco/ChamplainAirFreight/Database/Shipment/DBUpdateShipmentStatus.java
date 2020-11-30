package edu.cco.ChamplainAirFreight.Database.Shipment;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 21, 2020
 * @Description: DBUpdateShipmentStatus - class to interact with the database and the GUI page 
 * to update the shipment status information, using the stored procedure Update_Shipmint_Status.
 * @MODDIFIED: 
 */
//Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

public class DBUpdateShipmentStatus extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public int sStatus;
	public int sID;
	
/**
* DataBase Structure:
* 1. ShipmentStatusID int
* 2. ShipmentStatus int CHANGE	 
* MADE A CHANGE TO ShipmentStatus DataType to match DATABASE
* Change String to int
* Default Constructor
	* Matt Ridgway 
	* 11/21/2020
	*/
	public DBUpdateShipmentStatus() {
						
			//blank
	}//end default constructor	
	
	/** 
	* updateShipmentStatus
	* 
	* Matt Ridgway 
	* 11/21/2020*
	* @param sID
	* @param sStatus
	*/
	public void updateShipmentStatus(int sID,int sStatus) {
	    try {
	    	String storedP = "{call CAFDB.dbo.Update_Shipment_Status(?,?)}"; 
	     
	        callable = connection.prepareCall(storedP);
	        callable.setInt(1,sID);
	        callable.setInt(2,sStatus);	 

	        ResultSet rs = callable.executeQuery(); 

	    } catch (SQLException ex) {
	        System.out.println("Update Shipment Status Problem!");
	    }
	}//end updateShipmentStatus
	/**
	* Getters
	* Matt Ridgway 
	* 11/21/2020
	*/

	public int getsStatus() {
		return sStatus;
	}

	public int getsID() {
		return sID;
	}


}
