package edu.cco.ChamplainAirFreight.Database;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;

/**
 * class for finding various IDs in the Database
 * @author Kelly
 *
 */
public class DBFinder extends DBConnection {
	public CallableStatement callable = null;
	private ArrayList<String> airportNames = new ArrayList<>(); 
	private ArrayList<Integer> airportIDs = new ArrayList<>();
	
	
/**
 * Default constructor
 */
	public DBFinder() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllAircraft"); 
		}
	}
	
	/**
	 * findClientID - method for finding a clientID after adding a new client to the database
	 * @return
	 */
	public int findClientID(String name, int type, String phone){
		int id = 0; 
		try {
			String query = "USE[CAFDB] SELECT clientID FROM CAFDB.dbo.Clients WHERE "
					+ "ClientName = ? AND ClientTypeID=? AND ClientPhoneNumber=?"; 
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, type);
			preparedStatement.setString(3, phone);
			ResultSet rs = preparedStatement.executeQuery(); 
			while(rs.next()) {
				id = rs.getInt(1); 
			}
		}catch(SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not get client ID information"); 
		}
		return id; 
	}
	
	/**
	 * findAirPorts - view the airportIDs and the names of the airports. 
	 */
	public void findAirports() {
		
		try {
			String query = "USE[CAFDB] SELECT AirportID, AirportName FROM "
					+ "CAFDB.dbo.Airports"; 
			callable = connection.prepareCall(query); 
				
			ResultSet rs = callable.executeQuery(); 
			while(rs.next()) {
				airportIDs.add(rs.getInt(1)); 
				airportNames.add(rs.getString(2)); 
			}
		}catch(SQLException ex) {
			Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not get client ID information"); 
		}
	}
	
	public ArrayList<String> getAirportNames(){
		return airportNames; 
	}
	public ArrayList<Integer> getAirportIDs(){
		return airportIDs; 
	}
	
	
}
