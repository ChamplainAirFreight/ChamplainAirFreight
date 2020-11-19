package edu.cco.ChamplainAirFreight;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 19, 2020
 * @Description: DBUpdateAircraft - class to interact with the database and the GUI page to update Aircraft
 * using the stored procedure Update_Aircraft.
 * @MODDIFIED: 
 */

//Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUpdateAircraft extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public ArrayList<String> aircraftID=new ArrayList<>();
	public ArrayList<String> modelID=new ArrayList<>();
	public ArrayList<String> aircraft=new ArrayList<>();
	public String aircraftInfo;
	public int model;
	public int id;
	
	/**
	* Default Constructor
	* Matt Ridgway 
	* 11/19/2020
	*/	
public DBUpdateAircraft() {
	
	try {
		statement=connection.createStatement();
		setAircraft();
	}catch (SQLException e) {
        System.out.println("Could Not Connect to DataBase!");
    }
}//end default constructor
/**
* setAircraft pulls aircraft put's into a String with comma spacing
* Matt Ridgway 
* 11/19/2020
*/	
public void setAircraft() {
	
	try {
		String sql="SELECT AircraftID FROM Aircraft";
		ResultSet result=statement.executeQuery(sql);
		while(result.next()) {
			aircraftInfo+=result.getString(1)+", ";
			
			
		}
		
	}catch (SQLException ex) {
        Logger.getLogger(DBUpdateAircraft.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Could Not View Aircraft");
    }
	
	setAircraftArray();

}
/**
* setAircraftArray
* Matt Ridgway 
* 11/19/2020
*/	
public void setAircraftArray() {
	// split string aircraftInfo by , into list. Loop through and put into aircraftID List
	List<String> aircraftList=new ArrayList<>(Arrays.asList(aircraftInfo.split(",")));
	for(String strg : aircraftList) {
		aircraftID.add(String.valueOf(strg));
		
	}
	
}
/**
* clearAircraft 
* Clear List and String
* Matt Ridgway 
* 11/19/2020
*/	
public void clearAircraft() {
	
	aircraftID.clear();
	aircraftInfo="";
}
/**
* setAircraftSQL
* 
* Matt Ridgway 
* 11/19/2020
* @param aircraftSelected
*/	
public void setAircraftSQL(String aircraftSelected) {
	
	String sql="SELECT ";
	
}
}//end class
