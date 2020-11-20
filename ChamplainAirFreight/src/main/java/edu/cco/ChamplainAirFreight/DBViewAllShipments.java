package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Kelly
 * @Date Nov 13, 2020
 * @Description - class that pulls DBConnection in order to view all the shipment information, using the View_All_Shipment  
 * stored procedure
 * @TEST_STATUS - confirmed
 */
public class DBViewAllShipments extends DBConnection {
	private CallableStatement callable = null;
	private ArrayList<Integer> shipID = new ArrayList<>(); 
	private ArrayList<Integer> clientID = new ArrayList<>(); 
	private ArrayList<Double> shipVolume = new ArrayList<>(); 
	private ArrayList<Double> shipWeight = new ArrayList<>(); 
	private ArrayList<Integer> shipStatusID = new ArrayList<>(); 
	private ArrayList<Date> shipStartDate = new ArrayList<>(); 
	private ArrayList<Date> shipEndDate = new ArrayList<>(); 
	private ArrayList<String> shipNotes = new ArrayList<>(); 
	
	/**
	 * Default Constructor
	 */
	public DBViewAllShipments() {
		try {
			statement = connection.createStatement(); 
			viewAll(); //call the viewAll function; 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}
	
	/**
	 * viewAll - method to call the View_All_Shipment stored procedure
	 * Kelly May
	 * 11/13/2020
	 */
	public void viewAll() {
		try {
			String method = "{call CAFDB.dbo.View_All_Shipments}"; 
			callable = connection.prepareCall(method); 
			
			//execute the query
			ResultSet rs = callable.executeQuery(); 
			
			/**
			 * Output from View_All_Aircraft:
			 * 1 = shipmentID - int
			 * 2 = ClientID - int
			 * 3 = ShipmentVolume - int
			 * 4 = ShipmentWeight - int
			 * 5 = ShipmentStatusID - int
			 * 6 = ShipmentStartDate - date
			 * 7 = ShipmentEndDate - date
			 * 8 = ShipmentNotes - varchar (string)
			 */
			while(rs.next()) {
				shipID.add(rs.getInt(1));
				clientID.add(rs.getInt(2)); 
				shipVolume.add(rs.getDouble(3)); 
				shipWeight.add(rs.getDouble(4)); 
				shipStatusID.add(rs.getInt(5)); 
				shipStartDate.add(rs.getDate(6)); 
				shipEndDate.add(rs.getDate(7)); 
				shipNotes.add(rs.getString(8)); 
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("view all clients could not be completed"); 
		}
	}
	
	/**
	 * multiple get methods to use each arraylist independently 
	 */
public ArrayList<Integer> getShipID(){
	return shipID; 
}

public ArrayList<Integer> getClientID(){
	return clientID; 
}

public ArrayList<Double> getShipVolume(){
	return shipVolume; 
}

public ArrayList<Double> getShipWeight(){
	return shipWeight; 
}

public ArrayList<Integer> getShipStatusID(){
	return shipStatusID; 
}

public ArrayList<Date> getStartDate(){
	return shipStartDate; 
}

public ArrayList<Date> getEndDate(){
	return shipEndDate; 
}

public ArrayList<String> getNotes(){
	return shipNotes; 
}

/**
 * clearShipment - method to clear the ArrayLists
 */
public void clearShipment() {
	shipID.clear(); 
	clientID.clear();
	shipVolume.clear(); 
	shipWeight.clear();
	shipStatusID.clear();
	shipStartDate.clear();
	shipEndDate.clear(); 
	shipNotes.clear(); 
}

}
