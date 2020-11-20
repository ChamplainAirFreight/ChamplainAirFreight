package edu.cco.ChamplainAirFreight;

/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 20, 2020
 * @Description: DBUpdateClient - class to interact with the database and the GUI page 
 * to update the Client information, using the stored procedure Update_Client.
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

public class DBUpdateClient extends DBConnection{
	//Variables
			public CallableStatement callable = null;
			public ArrayList<Integer> clientID=new ArrayList<>();
			public String clientInfo;
			public String clientName;
			public int clientIDType;
			public String clientPhone;
		
			private int id;

/**
* Default Constructor
* Matt Ridgway 
* 11/20/2020
*/	
public DBUpdateClient() {
						
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
		String sql="SELECT ClientID FROM Clients";
		ResultSet result=statement.executeQuery(sql);
		while(result.next()) {
			clientInfo+=result.getString(1)+", ";
			
		}
		
	}catch (SQLException ex) {
        Logger.getLogger(DBUpdateClient.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Could Not View Client");
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
public void setClientSQL(int clientSelected) {
	
	try {
	String sql="SELECT ClientName, ClientTypeID, ClientPhoneNumber FROM Clients Where ClientID=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, clientSelected);
    ResultSet resultS = preparedStatement.executeQuery();
	
    	while(resultS.next()) {
    		id=resultS.getInt(1);
    		clientName=resultS.getString(2);
    		clientIDType=resultS.getInt(3);
    		clientPhone=resultS.getString(4);
    		
    	}
	} catch (SQLException ex) {
        Logger.getLogger(DBUpdateClient.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Get Client Problem!");
    }
	
}//end setClientSQL
/** RESULTSET
* updateAirport
* 
* Matt Ridgway 
* 11/20/2020
* @param cID
* @param cName
* @param cIDtype
* @param cPhone
*/
public ResultSet updateClient(int cID, String cName, int cIDType, String cPhone, int input) {
	 ResultSet results = null;
	 try {
		 String sqlState="SELECT ClientID FROM Clients WHERE ClientID=?";
		 PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(sqlState);
        preparedStatement.setInt(1, input);
        ResultSet resultS = preparedStatement.executeQuery();
       while (resultS.next()) {
           id = resultS.getInt(1);
       }
	}catch (SQLException ex) {
      Logger.getLogger(DBUpdateClient.class.getName()).log(Level.SEVERE, null, ex);
  }
	 try {
       String SQL = "UPDATE Clients SET ClientName = ?, "
       		+ "ClientTypeID = ?,"
       		+ "ClientPhoneNumber = ?";
       	
       callable = connection.prepareCall(SQL);
       callable.setString(2, cName);
       callable.setInt(3, cIDType);
       callable.setString(4, cPhone);
       results = callable.executeQuery();

       System.out.println(results);

   } catch (SQLException ex) {
       System.out.println("Results Not Returned");
   }
   
   return results;
}//end ResultSet
/**
* updateClient
* Matt Ridgway 
* 11/20/2020
*/
public void updateAirport() {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Client}"; 
        PreparedStatement ps;
        ps = connection.prepareStatement(storedP);
        callable = connection.prepareCall(storedP);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Client Problem!");
    }
}//end updateClient
/**
* Getters
* Matt Ridgway 
* 11/20/2020
*/

public ArrayList<Integer> getClientID() {
	return clientID;
}
public String getClientInfo() {
	return clientInfo;
}
public String getClientName() {
	return clientName;
}
public int getClientIDType() {
	return clientIDType;
}
public String getClientPhone() {
	return clientPhone;
}
public int getId() {
	return id;
}
}//end class
