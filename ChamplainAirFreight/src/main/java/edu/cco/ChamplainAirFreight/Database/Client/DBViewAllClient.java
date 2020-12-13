package edu.cco.ChamplainAirFreight.Database.Client;
/**
 * 
 * @author Kelly May
 * @Date: Nov 9, 2020
 * @Description: DBViewAllClient - database based class that uses the ViewAllClient stored procedure
 * to fill in the View Clients portion of the ClientsPage GUI
 *
 */
//imports
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

public class DBViewAllClient extends DBConnection {
	
	//variables
public CallableStatement callable = null; 
private ArrayList<Integer> id = new ArrayList<>(); 
private ArrayList<String> name = new ArrayList<>();
private ArrayList<String> phone =  new ArrayList<>(); 
private ArrayList<String> clientType = new ArrayList<>(); 
private ArrayList<String> address1 = new ArrayList<>(); 
private ArrayList<String> address2 = new ArrayList<>(); 
private ArrayList<String> city = new ArrayList<>(); 
private ArrayList<String> state = new ArrayList<>(); 
private ArrayList<String> zip = new ArrayList<>(); 

/**
 * Default Constructor
 * Kelly May
 * 11/9/2020
 */
public DBViewAllClient() {
	try {
		statement = connection.createStatement();
		viewAll(); // call the viewAll function. 
			
	} catch(SQLException ex) {
		System.out.println("Database connection failed DBViewAllClient");
	}
}

/**
 * viewAll - method that calls the View_All_Client stored procedure in SQL db
 * Kelly May
 * 11/9/2020
 */
public void viewAll() {
	try {
		String method = "{call CAFDB.dbo.View_All_Client}"; 
		callable = connection.prepareCall(method); 
		
		//execute the query
		ResultSet rs = callable.executeQuery();
		/**
		 * 1 = ClientID
		 * 2 = ClientName
		 * 3 = Client Type
		 * 4 = phone number
		 * 5 = address line 1
		 * 6 = address line 2
		 * 7 = city
		 * 8 = state
		 * 9 = zip
		 */
		
		while(rs.next()) {
			id.add(rs.getInt(1)); 
			name.add(rs.getString(2)); 
			clientType.add(rs.getString(3)); 
			phone.add(rs.getString(4)); 
			address1.add(rs.getString(5)); 
			address2.add(rs.getString(6)); 
			city.add(rs.getString(7)); 
			state.add(rs.getString(8)); 
			zip.add(rs.getString(9));   
		}
		
	}catch (SQLException ex) {
		Logger.getLogger(DBViewAllClient.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("view all clients could not be completed"); 
	}
}

//use just to print all strings to test - this will show in CLI. 
public void getResults() {
	for(int i = 0; i<name.size(); i++) {
		System.out.println(name.get(i)+ "-" + clientType.get(i) + "-" + address1.get(i) 
		+ " " + address2.get(i) + "-" + city.get(i) + "-" + state.get(i) + "-" + zip.get(i)); 
	}
}

public ArrayList<Integer> getID(){
	return id; 
}
public ArrayList<String> getName(){
	return name; 
}

public ArrayList<String> getClientType(){
	return clientType; 
}

public ArrayList<String> getClientPhone(){
	return phone; 
}

public ArrayList<String> getAddress1(){
	return address1; 
}

public ArrayList<String> getAddress2(){
	return address2; 
}

public ArrayList<String> getCity(){
	return city; 
}

public ArrayList<String> getState(){
	return state; 
}

public ArrayList<String> getZip(){
	return zip; 
}

/**
 * clearAllClients -clears the arraylists of all the client information. 
 */
public void clearAllClients() {
	name.clear(); 
	clientType.clear(); 
	address1.clear(); 
	address2.clear(); 
	city.clear(); 
	state.clear(); 
	zip.clear(); 
}

}
