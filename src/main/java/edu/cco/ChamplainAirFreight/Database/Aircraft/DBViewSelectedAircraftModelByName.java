package edu.cco.ChamplainAirFreight.Database.Aircraft;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;

public class DBViewSelectedAircraftModelByName extends DBConnection {
	
	public CallableStatement callable = null; 
	private int acModelID;
	private String acMake;
	private String acModel;
	private String acRange;
	private String acRangeClass;
	private Double acPayload;
	private Double acLoadVolume;
	
	/**
	 * Default Constructor
	 */
	public DBViewSelectedAircraftModelByName() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewSelectedAircraftModelByName"); 
		}
	}
	
	/**
	 * viewSelected - method to view an aircraft's information based on the AircraftID 
	 * @param airID
	 */
	public void viewSelected(String airID) {
		try {
		String method = "{call CAFDB.dbo.View_Selected_Aircraft_Model_by_Name(?)}"; 
		callable = connection.prepareCall(method); 
		callable.setString(1, airID); // call the aircraft ID for searching
		
		//execute the query
		ResultSet rs = callable.executeQuery(); 
		
		/**
		 * Output from View_Selected_Aircraft:
		 * 1 = ACModelID - int
		 * 2 = ACMake - Varchar (string)
		 * 3 = ACModel - varchar (string)
		 * 4 = ACRange - varchar (string)
		 * 5 = ACRangeClassification - varchar (string)
		 * 6 = ACPayload - float (int or double?)
		 * 7 = ACLoadVolume - float (int or double?)
		 */
		while(rs.next()) {
			acModelID = rs.getInt(1);
			acMake = rs.getString(2); 
			acModel = rs.getString(3); 
			acRange = rs.getString(4); 
			acRangeClass = rs.getString(5); 
			acPayload = rs.getDouble(6); 
			acLoadVolume = rs.getDouble(7); 				
		}
		
	}catch (SQLException ex) {
		Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("view selected aircraft model by name could not be completed"); 
	}
		
	}
	
	/**
	 * multiple getter methods for getting each found value
	 */
	
	public int getModelID() {
		return acModelID; 
	}
	public String getMake() {
		return acMake; 
	}
	public String getModel() {
		return acModel; 
	}
	public String getRange() {
		return acRange; 
	}
	public String getRangeClass() {
		return acRangeClass; 
	}
	public Double getPayload() {
		return acPayload; 
	}
	public Double getLoadVolume() {
		return acLoadVolume; 
	}

}
