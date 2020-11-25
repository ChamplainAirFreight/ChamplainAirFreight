package edu.cco.ChamplainAirFreight.Database.Aircraft;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: DBAddAircraft - class to interact with the database and the GUI page to insert a new Aircraft
 * using the stored procedure Add_Aircraft.
 * @MODDIFIED: 11/12/2020
 */

//Imports:
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;

public class DBAddAircraft extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public int model;
		
		private ArrayList<Integer> acMID = new ArrayList<>(); 
		private ArrayList<String> acModel = new ArrayList<>(); 
		private ArrayList<String> acMake = new ArrayList<>(); 
		
/**
* Default Constructor
* Matt Ridgway 
* 11/11/2020
*/
/*public DBAddAircraft(int modelNumber) {
	try {
		this.model=modelNumber;		
		
		//String storedP = "{call CAFDB.dbo.Add_Aircraft}"; 
		//callable = connection.prepareCall(storedP);
		insertSQL(model);			
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddAircraft.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Pilot"); 
	}
} */

//default constructor - just call the class
public DBAddAircraft() {
	findMakeModelList(); 
	
}
	
	

/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
* ACModelID int
*  
*/
public void insertSQL(int modelID) {
	try {
		String sql = "{call CAFDB.dbo.Add_AirCraft(?)}";
		callable=connection.prepareCall(sql);
		callable.setInt(1,  modelID);	
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert AirCraft Problem !");
    }
	
	
}

/**
 * findMakeModelList - method for getting a list of unique make and model of aircraft, to be used for the AddAircraft functionality
 */
public void findMakeModelList() {
	try {
	String query = "USE[CAFDB] SELECT ACModelID, ACMake, ACModel FROM CAFDB.dbo.AircraftModels"; 
	callable = connection.prepareCall(query); 
	
	ResultSet rs = callable.executeQuery(); 
	while(rs.next()) {
		acMID.add(rs.getInt(1)); 
		acModel.add(rs.getString(2)); 
		acMake.add(rs.getString(3)); 
	}
	} catch(SQLException ex) {
		Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Could not get client ID information"); 
	}
}

public ArrayList<Integer> getModelID(){
	return acMID; 
}
public ArrayList<String> getMake(){
	return acMake; 
}
public ArrayList<String> getModel(){
	return acModel; 
}
}
