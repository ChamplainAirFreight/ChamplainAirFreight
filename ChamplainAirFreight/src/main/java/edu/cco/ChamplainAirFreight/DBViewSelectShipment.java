package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Kelly May
 * @Date Nov 16, 2020
 * @Description - class that is used to view individual shipment information in the AWS database using
 * the View_Selected_Shipment stored procedure and an input value
 * @TEST_STATUS - not tested
 *  
 */
public class DBViewSelectShipment extends DBConnection {
	//variables
	public CallableStatement callable = null; 
	private int shipmentID; 
	private int clientID; 
	private Double shipVol; 
	private Double shipWeight; 
	private int shipStatusID; 
	private Date startDate; 
	private Date endDate; 
	private String notes; 
	
	
	/**
	 * Default Constructor
	 */
	public DBViewSelectShipment() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}
	
	/**
	 * viewSelected - method to view a shipment based on the shipment ID
	 * Kelly May
	 * 11/16/2020
	 */
	public void viewSelected(int sID) {
		try {
		String method = "{call CAFDB.dbo.View_Selected_Shipments(?)}"; 
		callable = connection.prepareCall(method); 
		callable.setInt(1, sID); // call the shipment ID for searching
		
		//execute the query
		ResultSet rs = callable.executeQuery(); 
		
		/**
		 * output from View_Selected_Shipments:
		 * 1 - shipmentID - int
		 * 2 - clientID - int
		 * 3 - shipmentVolume - Double
		 * 4 - shipmentWeight - Double
		 * 5 - shipmentStatusID - int
		 * 6 - shipmentStartDate - Date
		 * 7 - shipmentEndDate - Date
		 * 8 - shipmentNotes - String 
		 * 
		 */
		while(rs.next()) {
			shipmentID = rs.getInt(1); 
			clientID = rs.getInt(2); 
			shipVol = rs.getDouble(3); 
			shipWeight = rs.getDouble(4); 
			shipStatusID = rs.getInt(5); 
			startDate = rs.getDate(6); 
			endDate = rs.getDate(7); 
			notes = rs.getString(8); 
			}
		
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}
	}
	
	/**
	 * multiple getter methods to access each variable independently
	 */
public int getShipID() {
	return shipmentID; 
}
public int getClientID() {
	return clientID; 
}
public Double getShipVolume() {
	return shipVol; 
}
public Double getShipWeight() {
	return shipWeight; 
}
public int getStatusID() {
	return shipStatusID; 
}
public Date getStartDate() {
	return startDate; 
}
public Date getEndDate() {
	return endDate; 
}
public String getNotes() {
	return notes; 
}
}
