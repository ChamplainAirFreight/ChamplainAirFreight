package edu.cco.ChamplainAirFreight.Database.Shipment;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 21, 2020
 * @Description: DBUpdateShipment - class to interact with the database and the GUI page 
 * to update the shipment information, using the stored procedure Update_Shipmint.
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
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

public class DBUpdateShipment extends DBConnection{
	//Variables
			public CallableStatement callable = null;
			public ArrayList<Integer> shipID=new ArrayList<>();
			public String shipInfo;
			public int clientID;
			public float shipVolume;
			public float shipWeight;
			public int shipStatusID;
			public Date startDate;
			public Date endDate;
			public String shipNote;
			
			private int id;

/**
* DataBase Structure:
* 1. ShipmentID int
* 2. ClientID int
* 3. ShipmentVolume float
* 4. ShipmentWeight float
* 5. ShipmentStatusID int
* 6. ShipmentStartDate Date
* 7. ShipmentEndDate DATE
* 8. ShipmentNotes String
* 
* Default Constructor
* Matt Ridgway 
* 11/21/2020
*/
public DBUpdateShipment() {
					
			try {
				statement=connection.createStatement();
				setShipment();
				}catch (SQLException e) {
					    System.out.println("Could Not Connect to DataBase!");
				}
}//end default constructor	
/**
* setshipment pulls shipID put's into a String with comma spacing
* Matt Ridgway 
* 11/21/2020
* */
public void setShipment() {
			
		try {
				String sql="SELECT ShipmentID FROM Shipments";
				ResultSet result=statement.executeQuery(sql);
				while(result.next()) {
					shipInfo+=result.getString(1)+", ";
					
			}
				
		}catch (SQLException ex) {
		        Logger.getLogger(DBUpdateShipment.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Could Not View Shipment");
	    }
			
		setShipmentArray();
			
}//end setShipment
/**
* setShipmentArray
* Matt Ridgway 
* 11/21/2020
* split string shipInfo by comma into list. Loop through and put into shipID List
*/	
public void setShipmentArray() {
	
	List<String> shipList=new ArrayList<>(Arrays.asList(shipInfo.split(",")));
	for(String strg : shipList) {
		shipID.add(Integer.valueOf(strg));
		
	}
	
}//end setShipmentArray
/**
* clearShipment
* Clear List and String
* Matt Ridgway 
* 11/21/2020
*/	
public void clearShipment() {
	
	shipID.clear();
	shipInfo="";
}//end clearShipment
/**
* setShipmentSQL
* 
* Matt Ridgway 
* 11/21/2020
* @param shipmentSelected
*/
public void setShipmentSQL(int shipmentSelected) {
	
	try {
	String sql="SELECT ShipmentID, ClientID, ShipmentVolume,ShipmentWeight, ShipmentStatusID, "
			+ "ShipmentStartDate, ShipmentEndDate, ShipmentNotes"
			+ "FROM Shipments Where ShipmentID=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, shipmentSelected);
    ResultSet resultS = preparedStatement.executeQuery();
	
    	while(resultS.next()) {
    		id=resultS.getInt(1);
    		clientID=resultS.getInt(2);
    		shipVolume=resultS.getFloat(3);
    		shipWeight=resultS.getFloat(4);
    		shipStatusID=resultS.getInt(5);
    		startDate=resultS.getDate(6);
    		endDate=resultS.getDate(7);
    		shipNote=resultS.getString(8);
    		
    	}
	} catch (SQLException ex) {
        Logger.getLogger(DBUpdateShipment.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Get Shipment Problem!");
    }
	
}//end setShipmentSQL
/** RESULTSET
* updateShipment
* 
* Matt Ridgway 
* 11/21/2020*
* @param cID
* @param sVol
* @param sWeight
* @param status
* @param sStartDate
* @param sEndDate
* @param note
* 
*/
public ResultSet updateShipment(int cID, float sVol,float sWeight, int status, Date sStartDate, Date sEndDate, String note, int input) {
	 ResultSet results = null;
	 try {
		 String sqlState="SELECT ShipmentID FROM Shipments WHERE ShipmentID=?";
		 PreparedStatement preparedStatement;
     preparedStatement = connection.prepareStatement(sqlState);
     preparedStatement.setInt(1, input);
     ResultSet resultS = preparedStatement.executeQuery();
    while (resultS.next()) {
        id = resultS.getInt(1);
    }
	}catch (SQLException ex) {
   Logger.getLogger(DBUpdateShipment.class.getName()).log(Level.SEVERE, null, ex);
}
	 try {
    String SQL = "UPDATE Shipments SET "
    		+ "ClientID=?"
    		+ "ShipmentVolume=?,"
    		+ "ShipmentWeight=?,"
    		+ "ShipmentStatusID=?,"
    		+ "ShipmentStartDate=?,"
    		+ "ShipmentEndDate=?,"
    		+ "ShipmentNotes=?";
    	
    callable = connection.prepareCall(SQL);
    callable.setInt(2,cID);
    callable.setFloat(3, sVol);
    callable.setFloat(4,sWeight);
    callable.setInt(5, status);
    callable.setDate(6, sStartDate);
    callable.setDate(7, sEndDate);
    callable.setString(8, note);
    
    results = callable.executeQuery();

    System.out.println(results);

} catch (SQLException ex) {
    System.out.println("Results Not Returned");
}

return results;
}//end ResultSet
/**
* updateShipment
* Matt Ridgway 
* 11/21/2020
*/
public void updateShipment() {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Shipment}"; 
        PreparedStatement ps;
        ps = connection.prepareStatement(storedP);
        callable = connection.prepareCall(storedP);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Shipment Problem!");
    }
}//end updateShipment
/**
* Getters
* Matt Ridgway 
* 11/21/2020
*/

public ArrayList<Integer> getShipID() {
				return shipID;
			}
			public String getShipInfo() {
				return shipInfo;
			}
			public int getClientID() {
				return clientID;
			}
			public float getShipVolume() {
				return shipVolume;
			}
			public float getShipWeight() {
				return shipWeight;
			}
			public int getShipStatusID() {
				return shipStatusID;
			}
			public Date getStartDate() {
				return startDate;
			}
			public Date getEndDate() {
				return endDate;
			}
			public String getShipNote() {
				return shipNote;
			}
			public int getId() {
				return id;
			}
}
