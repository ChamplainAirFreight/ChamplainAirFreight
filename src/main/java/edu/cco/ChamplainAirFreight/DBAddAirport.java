package edu.cco.ChamplainAirFreight;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: DBAddAirport - class to interact with the database and the GUI page to insert a new Airport
 * using the stored procedure Add_Airport.
 * @MODDIFIED: 11/12/2020
 */


public class DBAddAirport extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public String portName;
		public String portLocation;
		public Boolean hubBit;
		public float portDistance;
		
/**
* Default Constructor
* Matt Ridgway 
* 11/11/2020
*/
public DBAddAirport(String name, String location, Boolean hub, float distance) {
	try {
		this.portName=name;
		this.portLocation=location;
		this.hubBit=hub;
		this.portDistance=distance;
		String storedP = "{call CAFDB.dbo.Add_Airport}"; 
		callable = connection.prepareCall(storedP);
		
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddAirport.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New AirPort"); 
	}
}//end DBAddAirport
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
* 1. AirportID int
* 2. AirportName string
* 3. AirportLocation string
* 4. AirportHub bit
* 5. AirportDistance float 
*/
public void insertSQL(String name, String location, Boolean hub, float distance) {
	try {
		String sql = "{call CAFDB.dbo.Add_Pilot(?,?,?,?)}";
		callable=connection.prepareCall(sql);
		callable.setString(1,  name);
		callable.setString(2, location);
		callable.setBoolean(3, hub);
		callable.setFloat(4, distance);
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert AirPort Problem !");
    }
	
	
}//end insertSQL
}
