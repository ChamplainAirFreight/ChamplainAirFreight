package edu.cco.ChamplainAirFreight.Database.Client;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 20, 2020
 * @Description: DBUpdateClientAddress - class to interact with the database and the GUI page 
 * to update the Client address information, using the stored procedure Update_Client_Address.
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

import edu.cco.ChamplainAirFreight.Database.DBConnection;
public class DBUpdateClientAddress  extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	//public ArrayList<Integer> clientID=new ArrayList<>();
	public String clientAdd1;
	public String clientAdd2;
	public String clientCity;
	public String clientState;
	public String clientInfo;
	public int clientZip;
	
	//private int id;

/**
 * DataBase structure:
 * 1. ClientAddressID int
 * 2. ClientID int
 * 3. ClientAddressLine1 String
 * 4. ClientAddressLine2 String
 * 5. ClientAddressCity String
 * 6. ClientAddressState String
 * 7. ClientAddressZip int
* Default Constructor
* Matt Ridgway 
* 11/20/2020
*
*	public DBUpdateClientAddress() {
*		
*		try {
*			statement=connection.createStatement();
*			setClientAddress();
*		}catch (SQLException e) {
*		    System.out.println("Could Not Connect to DataBase!");
*		}
*}//end default constructor
*/
	/** Blank
	 * Default Constructor
	 */
	public DBUpdateClientAddress() {
		//Just call class do nothing
	}		
/**
* setClientAddress pulls clientID put's into a String with comma spacing
* Matt Ridgway 
* 11/20/2020
*/
//
//public void setClientAddress() {
//	
//	try {
//		String sql="SELECT ClientID FROM ClientAddress";
//		ResultSet result=statement.executeQuery(sql);
//		while(result.next()) {
//			clientInfo+=result.getString(1)+", ";
//			
//		}
//		
//	}catch (SQLException ex) {
//        Logger.getLogger(DBUpdateClientAddress.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (Exception e) {
//        e.printStackTrace();
//        System.out.println("Could Not View Clients Address");
//    }
//	
//	setClientAddressArray();
//	
//}//end setClient
///**
//* setClientAddressArray
//* Matt Ridgway 
//* 11/20/2020
//* split string clientInfo by comma into list. Loop through and put into clientID List
//*/	
//public void setClientAddressArray() {
//	
//	List<String> clientList=new ArrayList<>(Arrays.asList(clientInfo.split(",")));
//	for(String strg : clientList) {
//		clientID.add(Integer.valueOf(strg));
//		
//	}
//	
//}//end setClientArray
///**
//* clearClientAddress
//* Clear List and String
//* Matt Ridgway 
//* 11/20/2020
//*/	
//public void clearClientAddress() {
//	
//	clientID.clear();
//	clientInfo="";
//}//end clearClient
///**
//* setCleintSQL
//* 
//* Matt Ridgway 
//* 11/20/2020
//* @param clientSelected
//*/
//public void setClientAddressSQL(int clientSelected) {
//	
//	try {
//	String sql="SELECT ClientAddressLine1, ClientAddressLine2, ClientAddressCity, ClientAddressState, ClientAddressZip FROM ClientAddresses Where ClientID=?";
//	PreparedStatement preparedStatement = connection.prepareStatement(sql);
//    preparedStatement.setInt(1, clientSelected);
//    ResultSet resultS = preparedStatement.executeQuery();
//	
//    	while(resultS.next()) {
//    		id=resultS.getInt(2);
//    		clientAdd1=resultS.getString(3);
//    		clientAdd2=resultS.getString(4);
//    		clientCity=resultS.getString(5);
//    		clientState=resultS.getString(6);
//    		clientZip=resultS.getInt(7);
//    		
//    	}
//	} catch (SQLException ex) {
//        Logger.getLogger(DBUpdateClientAddress.class.getName()).log(Level.SEVERE, null, ex);
//        System.out.println("Get Client Address Problem!");
//    }
//	
//}//end setClientSQL
///** RESULTSET
//* updateClientAddress
//* 
//* Matt Ridgway 
//* 11/20/2020
//* @param cID
//* @param add1
//* @param add2
//* @param city
//* @param state
//* @param zip
//* 
//*/
//public ResultSet updateClientAddress(int cID, String add1, String add2,String city,String state,int zip) {
//	 ResultSet results = null;
//	 try {
//		 String sqlState="SELECT ClientID FROM ClientAddresses WHERE ClientID=?";
//		 PreparedStatement preparedStatement;
//       preparedStatement = connection.prepareStatement(sqlState);
//       preparedStatement.setInt(1, cID);
//       ResultSet resultS = preparedStatement.executeQuery();
//      while (resultS.next()) {
//          id = resultS.getInt(1);
//      }
//	}catch (SQLException ex) {
//     Logger.getLogger(DBUpdateClientAddress.class.getName()).log(Level.SEVERE, null, ex);
// }
//	 try {
//      String SQL = "UPDATE ClientAddresses SET ClientAddressLine1 = ?, "
//      		+ "ClientAddressLine2 = ?,"
//      		+ "ClientAddressCity = ?,"
//      		+ "ClientAddressState =?,"
//      		+ "ClientAddressZip=?";
//      	
//      callable = connection.prepareCall(SQL);
//      callable.setString(3, add1);
//      callable.setString(4, add2);
//      callable.setString(5, city);
//      callable.setString(6, state);
//      callable.setInt(7, zip);
//      results = callable.executeQuery();
//
//      System.out.println(results);
//
//  } catch (SQLException ex) {
//      System.out.println("Results Not Returned");
//  }
//  
//  return results;
//}//end ResultSet
/**
* updateClientAddress
* Matt Ridgway 
* 11/20/2020
*/
public void updateClientA(int cID, String add1, String add2,String city,String state,int zip) {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Client_Addresses(?,?,?,?,?,?))}"; 
        PreparedStatement ps;
        ps = connection.prepareStatement(storedP);
        callable = connection.prepareCall(storedP);
        String SQL = "UPDATE ClientAddresses SET ClientAddressLine1 = ?, "
          		+ "ClientAddressLine2 = ?,"
          		+ "ClientAddressCity = ?,"
          		+ "ClientAddressState =?,"
          		+ "ClientAddressZip=?"
          		+ "WHERE ClientID=cID";
          	
          callable = connection.prepareCall(SQL);
          callable.setString(1, add1);
          callable.setString(2, add2);
          callable.setString(3, city);
          callable.setString(4, state);
          callable.setInt(5, zip);
          callable.executeQuery();

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Client Address Problem!");
    }
}//end updateClientAddress
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/
//public ArrayList<Integer> getClientID() {
//	return clientID;
//}
public String getClientAdd1() {
	return clientAdd1;
}
public String getClientAdd2() {
	return clientAdd2;
}
public String getClientCity() {
	return clientCity;
}
public String getClientState() {
	return clientState;
}
public String getClientInfo() {
	return clientInfo;
}
public int getClientZip() {
	return clientZip;
}
//public int getId() {
//	return id;
//}
}//end class
