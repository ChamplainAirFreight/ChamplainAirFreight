package edu.cco.ChamplainAirFreight;
/**
 * 
 * @author Matt Ridgway
 * @Date: November 19, 2020
 * @Description: DBUpdateAircraft - class to interact with the database and the GUI page to update Aircraft
 * using the stored procedure Update_Aircraft.
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

public class DBUpdateAircraft extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public ArrayList<String> aircraftID=new ArrayList<>();
	public String aircraftInfo;
	public String model;
	public String id;
	public String status;
	private int idStatus;
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
	
	try {
	String sql="SELECT ACModel, AircraftStatus FROM Aircraft Where AircraftId=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, aircraftSelected);
    ResultSet resultS = preparedStatement.executeQuery();
	
    	while(resultS.next()) {
    		id=resultS.getString(1);
    		model=resultS.getString(2);
    		status=resultS.getString(3);
    	}
	} catch (SQLException ex) {
        Logger.getLogger(DBUpdateAircraft.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Get Aircraft Problem!");
    }
	
}
/** RESULTSET
* updateAircraft
* 
* Matt Ridgway 
* 11/19/2020
* @param aID
* @param mID
* @param aStatus
* @param input
*/
public ResultSet updateAircraft(int aID, int mID, int aStatus, int input) {
	 ResultSet results = null;
	 try {
		 String sqlState="SELECT AircraftID FROM Aircraft WHERE AircraftID=?";
		 PreparedStatement preparedStatement;
         preparedStatement = connection.prepareStatement(sqlState);
         preparedStatement.setInt(1, input);
         ResultSet resultS = preparedStatement.executeQuery();
         while (resultS.next()) {
             idStatus = resultS.getInt(1);
         }
	}catch (SQLException ex) {
        Logger.getLogger(DBUpdateAircraft.class.getName()).log(Level.SEVERE, null, ex);
    }
	 try {
         String SQL = "UPDATE Aircraft SET ACModelID = ?, AircraftStatusID = ?";

         callable = connection.prepareCall(SQL);
         callable.setInt(2,mID);
         callable.setInt(3, aStatus);
         results = callable.executeQuery();

         System.out.println(results);

     } catch (SQLException ex) {
         System.out.println("Results Not Returned");
     }
     
     return results;
}
public void updateAircraft() {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Aircraft}"; 
        PreparedStatement ps;
        ps = connection.prepareStatement(storedP);
        callable = connection.prepareCall(storedP);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Aircraft Problem!");
    }
}
public String getModel() {
	return model;
}

public String getId() {
	return id;
}

public String getStatus() {
	return status;
}

public String getAircraftInfo() {
	return aircraftInfo;
}
public int getIdStatus() {
	return idStatus;
}

}//end class
