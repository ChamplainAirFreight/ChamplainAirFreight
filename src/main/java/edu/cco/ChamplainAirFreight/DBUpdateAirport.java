package edu.cco.ChamplainAirFreight;
/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 20, 2020
 * @Description: DBUpdateAirport - class to interact with the database and the GUI page 
 * to update the airport
 * using the stored procedure Update_Airport.
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
public class DBUpdateAirport extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public ArrayList<Integer> airportID=new ArrayList<>();
		public String airportInfo;
		public String airportName;
		public String airportLocation;
		public boolean airportHub;
		public float airportDistance;
		private int id;
		

/**
* Default Constructor
* Matt Ridgway 
* 11/20/2020
*/	
public DBUpdateAirport() {
			
			try {
				statement=connection.createStatement();
				setAirport();
			}catch (SQLException e) {
		        System.out.println("Could Not Connect to DataBase!");
		    }
}//end default constructor
/**
* setAircraft pulls airportID put's into a String with comma spacing
* Matt Ridgway 
* 11/20/2020
*/
public void setAirport() {
	
	try {
		String sql="SELECT AirportID FROM Airports";
		ResultSet result=statement.executeQuery(sql);
		while(result.next()) {
			airportInfo+=result.getString(1)+", ";
			
			
		}
		
	}catch (SQLException ex) {
        Logger.getLogger(DBUpdateAirport.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Could Not View Airports");
    }
	
	setAirportArray();
	
}//end setAirport
/**
* setAirportArray
* Matt Ridgway 
* 11/20/2020
* split string airportInfo by comma into list. Loop through and put into aircraftID List
*/	
public void setAirportArray() {
	
	List<String> airportList=new ArrayList<>(Arrays.asList(airportInfo.split(",")));
	for(String strg : airportList) {
		airportID.add(Integer.valueOf(strg));
		
	}
	
}//end setAirportArray
/**
* clearAirport 
* Clear List and String
* Matt Ridgway 
* 11/20/2020
*/	
public void clearAirport() {
	
	airportID.clear();
	airportInfo="";
}//end clearAirport
/**
* setAirportSQL
* 
* Matt Ridgway 
* 11/20/2020
* @param airportSelected
*/	
public void setAirportSQL(int airportSelected) {
	
	try {
	String sql="SELECT AirportName, AirportLocation, AirportHub, AirportDistanceFromHub FROM Airports Where AirportID=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, airportSelected);
    ResultSet resultS = preparedStatement.executeQuery();
	
    	while(resultS.next()) {
    		id=resultS.getInt(1);
    		airportName=resultS.getString(2);
    		airportLocation=resultS.getString(3);
    		airportHub=resultS.getBoolean(4);
    		airportDistance=resultS.getFloat(5);
    	}
	} catch (SQLException ex) {
        Logger.getLogger(DBUpdateAirport.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Get Airport Problem!");
    }
	
}//end setAirportSQL
/** RESULTSET
* updateAirport
* 
* Matt Ridgway 
* 11/20/2020
* @param aID
* @param aName
* @param aLoc
* @param aHub
* @param aDist
* @param input
*/
public ResultSet updateAircraft(int aID, String aName, String aLoc, boolean aHub, float aDist, int input) {
	 ResultSet results = null;
	 try {
		 String sqlState="SELECT AirportID FROM Airports WHERE AirportID=?";
		 PreparedStatement preparedStatement;
         preparedStatement = connection.prepareStatement(sqlState);
         preparedStatement.setInt(1, input);
         ResultSet resultS = preparedStatement.executeQuery();
        while (resultS.next()) {
            id = resultS.getInt(1);
        }
	}catch (SQLException ex) {
       Logger.getLogger(DBUpdateAirport.class.getName()).log(Level.SEVERE, null, ex);
   }
	 try {
        String SQL = "UPDATE Airports SET AirportName = ?, "
        		+ "AirportLocation = ?"
        		+ "AirportHub = ?"
        		+ "AirportDistanceFromHub=?";

        callable = connection.prepareCall(SQL);
        callable.setString(2, aName);
        callable.setString(3, aLoc);
        callable.setBoolean(4, aHub);
        callable.setFloat(5, aDist);
        results = callable.executeQuery();

        System.out.println(results);

    } catch (SQLException ex) {
        System.out.println("Results Not Returned");
    }
    
    return results;
}//end ResultSet
/**
* updateAirport
* Matt Ridgway 
* 11/20/2020
*/
public void updateAirport() {
    try {
    	String storedP = "{call CAFDB.dbo.Update_AirAirport}"; 
        PreparedStatement ps;
        ps = connection.prepareStatement(storedP);
        callable = connection.prepareCall(storedP);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Airport Problem!");
    }
}//end updateAirport
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/

public String getAirportInfo() {
	return airportInfo;
}
public String getAirportName() {
	return airportName;
}
public String getAirportLocation() {
	return airportLocation;
}
public float getAirportDistance() {
	return airportDistance;
}
public int getId() {
	return id;
}

}
