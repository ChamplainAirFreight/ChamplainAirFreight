package edu.cco.ChamplainAirFreight;
/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 21, 2020
 * @Description: DBUpdateShipmentStatus - class to interact with the database and the GUI page 
 * to update the shipment status information, using the stored procedure Update_Shipmint_Status.
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

public class DBUpdateShipmentStatus extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public ArrayList<Integer> shipID=new ArrayList<>();
	public String shipInfo;
	public int shipStatus;
	private int id;
	
/**
* DataBase Structure:
* 1. ShipmentStatusID int
* 2. ShipmentStatus int CHANGE	 
* MADE A CHANGE TO ShipmentStatus DataType to match DATABASE
* Change String to int
* Default Constructor
	* Matt Ridgway 
	* 11/21/2020
	*/
	public DBUpdateShipmentStatus() {
						
				try {
					statement=connection.createStatement();
					setShipmentStatus();
					}catch (SQLException e) {
						    System.out.println("Could Not Connect to DataBase!");
					}
	}//end default constructor	
	
	/**
	* setShipmentStatus pulls shipID put's into a String with comma spacing
	* Matt Ridgway 
	* 11/21/2020
	* */
	public void setShipmentStatus() {
				
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
				
			setShipmentStatusArray();
				
	}//end setShipmentStatus
	/**
	* setShipmentStatusArray
	* Matt Ridgway 
	* 11/21/2020
	* split string shipInfo by comma into list. Loop through and put into shipID List
	*/	
	public void setShipmentStatusArray() {
		
		List<String> shipList=new ArrayList<>(Arrays.asList(shipInfo.split(",")));
		for(String strg : shipList) {
			shipID.add(Integer.valueOf(strg));
			
		}
		
	}//end setShipmentStatusArray
	/**
	* clearShipmentStatus
	* Clear List and String
	* Matt Ridgway 
	* 11/21/2020
	*/	
	public void clearShipmentStatus() {
		
		shipID.clear();
		shipInfo="";
	}//end clearShipmentStatus
	/**
	* setShipmentStatusSQL
	* 
	* Matt Ridgway 
	* 11/21/2020
	* @param shipmentStatusSelected
	*/
	public void setShipmentStatusSQL(int shipmentStatusSelected) {
		
		try {
		String sql="SELECT ShipmentID, ShipmentStatusID"
				+ "FROM Shipments Where ShipmentID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    preparedStatement.setInt(1, shipmentStatusSelected);
	    ResultSet resultS = preparedStatement.executeQuery();
		
	    	while(resultS.next()) {
	    		id=resultS.getInt(1);	    		
	    		shipStatus=resultS.getInt(2);
	    		
	    		
	    	}
		} catch (SQLException ex) {
	        Logger.getLogger(DBUpdateShipmentStatus.class.getName()).log(Level.SEVERE, null, ex);
	        System.out.println("Get Shipment Status Problem!");
	    }
		
	}//end setShipmentStatusSQL
	/** RESULTSET
	* updateShipmentStatus
	* 
	* Matt Ridgway 
	* 11/21/2020*
	* @param input
	* @param status

	*/
	public ResultSet updateShipmentStatus(int status, int input) {
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
	   Logger.getLogger(DBUpdateShipmentStatus.class.getName()).log(Level.SEVERE, null, ex);
	}
		 try {
	    String SQL = "UPDATE Shipments SET ShipmentStatusID=?";	    		
	    	
	    callable = connection.prepareCall(SQL);
	    callable.setInt(2,status);	    
	    
	    results = callable.executeQuery();

	    System.out.println(results);

	} catch (SQLException ex) {
	    System.out.println("Results Not Returned");
	}

	return results;
	}//end ResultSet
	/**
	* updateShipmentStatus
	* Matt Ridgway 
	* 11/21/2020
	*/
	public void updateShipmentStatus() {
	    try {
	    	String storedP = "{call CAFDB.dbo.Update_Shipment_Status}"; 
	        PreparedStatement ps;
	        ps = connection.prepareStatement(storedP);
	        callable = connection.prepareCall(storedP);

	        ResultSet rs = callable.executeQuery(); 

	    } catch (SQLException ex) {
	        System.out.println("Update Shipment Status Problem!");
	    }
	}//end updateShipmentStatus
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

	public int getShipStatus() {
		return shipStatus;
	}

	public int getId() {
		return id;
	}

}
