package edu.cco.ChamplainAirFreight.Database.Aircraft;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: DBAddAircraftModel - class to interact with the database and the GUI page to insert a new Aircraft Model
 * using the stored procedure Add_AircraftModel.
 * @MODDIFIED: 11/12/2020
 */

// Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;


public class DBAddAircraftModel extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public String aMake;
		public String aModel;
		public String aRange;
		public String aRangeClass;
		public float aPayload;
		public float aVolume;
		
/**
* Default Constructor
* Matt Ridgway 
* 11/11/2020
*/
public DBAddAircraftModel(String make, String model, String range, String rangeClass,float load, float vol) {
	try {
		this.aMake=make;
		this.aModel=model;
		this.aRange=range;
		this.aRangeClass=rangeClass;
		this.aPayload=load;
		this.aVolume=vol;
		
		String storedP = "{call CAFDB.dbo.Add_Aircraft_Model}"; 
		callable = connection.prepareCall(storedP);
		insertSQL(aMake,aModel,aRange,aRangeClass,aPayload,aVolume);	
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddAircraftModel.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New AirCraft Model"); 
	}
	
}//end constructor
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
* 1 Make
* 2 Model
* 3 Range
* 4 Range CLass
* 5 Payload
* 6 Volume
*
*/
public void insertSQL(String make, String model, String range, String rangeClass,float load, float vol) {
	try {
		String sql = "{call CAFDB.dbo.Add_Aircraft_Model(?,?,?,?,?,?)}";
		callable=connection.prepareCall(sql);
		callable.setString(1,  make);
		callable.setString(2, model);
		callable.setString(3, range);
		callable.setString(4, rangeClass);
		callable.setFloat(5, load);
		callable.setFloat(6, vol);
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert  AirCraft Model Problem !");
    }
	
	
	
	
}
}
