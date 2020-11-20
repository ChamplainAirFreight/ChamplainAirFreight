package edu.cco.ChamplainAirFreight;
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
public class DBUpdateClientAddress  extends DBConnection {
	//Variables
	public CallableStatement callable = null;
	public ArrayList<Integer> addressID=new ArrayList<>();
	public ArrayList<Integer> clientID=new ArrayList<>();
	public String clientAdd1;
	public String clientAdd2;
	public String clientCity;
	public String clientState;
	public String clientInfo;
	public int clientZip;
	
	private int id;


/**
* Default Constructor
* Matt Ridgway 
* 11/20/2020
*/
	public DBUpdateClientAddress() {
		
		try {
			statement=connection.createStatement();
			setClient();
		}catch (SQLException e) {
		    System.out.println("Could Not Connect to DataBase!");
		}
}//end default constructor
/**
* setClient pulls clientID put's into a String with comma spacing
* Matt Ridgway 
* 11/20/2020
*/

public void setClient() {
	
	try {
		String sql="SELECT ClientID FROM ClientAddress";
		ResultSet result=statement.executeQuery(sql);
		while(result.next()) {
			clientInfo+=result.getString(1)+", ";
			
		}
		
	}catch (SQLException ex) {
        Logger.getLogger(DBUpdateClientAddress.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Could Not View Clients Address");
    }
	
	setClientArray();
	
}//end setClient
/**
* setClientArray
* Matt Ridgway 
* 11/20/2020
* split string clientInfo by comma into list. Loop through and put into clientID List
*/	
public void setClientArray() {
	
	List<String> clientList=new ArrayList<>(Arrays.asList(clientInfo.split(",")));
	for(String strg : clientList) {
		clientID.add(Integer.valueOf(strg));
		
	}
	
}//end setClientArray
/**
* clearClient
* Clear List and String
* Matt Ridgway 
* 11/20/2020
*/	
public void clearClient() {
	
	clientID.clear();
	clientInfo="";
}//end clearClient
/**
* setCleintSQL
* 
* Matt Ridgway 
* 11/20/2020
* @param clientSelected
*/
public void setClientAddressSQL(int clientSelected) {
	
	try {
	String sql="SELECT ClientAddressLine1, ClientAddressLine2, ClientAddressCity, ClientAddressState, ClientAddressZip FROM ClientAddresses Where ClientID=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, clientSelected);
    ResultSet resultS = preparedStatement.executeQuery();
	
    	while(resultS.next()) {
    		id=resultS.getInt(2);
    		clientAdd1=resultS.getString(3);
    		clientAdd2=resultS.getString(4);
    		clientCity=resultS.getString(5);
    		clientState=resultS.getString(6);
    		clientZip=resultS.getInt(7);
    		
    	}
	} catch (SQLException ex) {
        Logger.getLogger(DBUpdateClientAddress.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Get Client Address Problem!");
    }
	
}//end setClientSQL
}//end class
