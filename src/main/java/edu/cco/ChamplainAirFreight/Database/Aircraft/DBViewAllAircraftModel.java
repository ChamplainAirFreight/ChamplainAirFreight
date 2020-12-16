package edu.cco.ChamplainAirFreight.Database.Aircraft;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;

public class DBViewAllAircraftModel extends DBConnection {
	
	//variables
		public CallableStatement callable = null;
		private ArrayList<Integer> acModelID = new ArrayList<>(); 
		private ArrayList<String> acMake = new ArrayList<>();
		private ArrayList<String> acModel = new ArrayList<>(); 
		private ArrayList<String> acRange = new ArrayList<>(); 
		private ArrayList<String> acRangeClass = new ArrayList<>(); 
		private ArrayList<Double> acPayload = new ArrayList<>(); 
		private ArrayList<Double> acLoadVolume = new ArrayList<>(); 	
		
		/**
		 * Default Constructor
		 * Kelly May
		 * 11/11/2020
		 */
		public DBViewAllAircraftModel() {
			try {
				statement = connection.createStatement(); 
				viewAll(); //call the viewAll function; 
				
			}catch(SQLException ex) {
				System.out.println("Database connection failed DBViewAllAircraftModel"); 
			}
		}

		/**
		 * viewAll - method that calls the View_All_Aircraft stored procedure in the SQL db
		 * Kelly May
		 * 11/11/2020
		 */
		public void viewAll() {
			try {
				String method = "{call CAFDB.dbo.View_All_Aircraft_Model}"; 
				callable = connection.prepareCall(method); 
				
				//execute the query
				ResultSet rs = callable.executeQuery(); 
				
				/**
				 * Output from View_All_Aircraft:
				 * 1 = ACModelID - int
				 * 2 = ACMake - Varchar (string)
				 * 3 = ACModel - varchar (string)
				 * 4 = ACRange - varchar (string)
				 * 5 = ACRangeClassification - varchar (string)
				 * 6 = ACPayload - float (int or double?)
				 * 7 = ACLoadVolume - float (int or double?)
				 */
				while(rs.next()) {
					acModelID.add(rs.getInt(1));
					acMake.add(rs.getString(2)); 
					acModel.add(rs.getString(3)); 
					acRange.add(rs.getString(4)); 
					acRangeClass.add(rs.getString(5)); 
					acPayload.add(rs.getDouble(6)); 
					acLoadVolume.add(rs.getDouble(7)); 				
				}
				
			}catch (SQLException ex) {
				Logger.getLogger(DBViewAllAircraftModel.class.getName()).log(Level.SEVERE, null, ex);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("view all aircraft model could not be completed"); 
			}
		}
		
		/**
		 * multiple get methods to access each ArrayList individually
		 */
		public ArrayList<Integer> getAircraftModelID(){
			return acModelID; 
		}
		
		public ArrayList<String> getAircraftMake(){
			return acMake; 
		}
		
		public ArrayList<String> getAircraftModel(){
			return acModel; 
		}
		
		public ArrayList<String> getAircraftRange(){
			return acRange; 
		}
		
		public ArrayList<String> getAircraftRangeClass(){
			return acRangeClass; 
		}
		
		public ArrayList<Double> getAircraftPayload(){
			return acPayload; 
		}
		
		public ArrayList<Double> getAircraftLoadVolume(){
			return acLoadVolume; 
		}
		
		/**
		 * clearAllAircraft - method that clears the arraylists with aircraft information 
		 * Kelly May
		 * 11/11/2020
		 */
		public void clearAllAircraft() {
			acModelID.clear(); 
			acMake.clear(); 
			acModel.clear(); 
			acRange.clear(); 
			acRangeClass.clear(); 
			acPayload.clear(); 
			acLoadVolume.clear(); 
		}

}
