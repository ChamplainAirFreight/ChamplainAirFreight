package edu.cco.ChamplainAirFreight;
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

public class DBViewAllClient extends DBConnection {
	
	//variables
public CallableStatement callable = null; 
public ArrayList<String> name = new ArrayList<>(); 
public ArrayList<String> clientType = new ArrayList<>(); 
public ArrayList<String> address1 = new ArrayList<>(); 
public ArrayList<String> address2 = new ArrayList<>(); 
public ArrayList<String> city = new ArrayList<>(); 
public ArrayList<String> state = new ArrayList<>(); 
public ArrayList<String> zip = new ArrayList<>(); 

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
		 * 3 = Client Type ID
		 * 4 = phone number
		 * 5 = address id
		 * 6 = client ID
		 * 7 = address line 1
		 * 8 = address line 2
		 * 9 = city
		 * 10 = state
		 * 11 = zip
		 */
		
		while(rs.next()) {
			name.add(rs.getString(2)); 
			clientType.add(rs.getString(3)); 
			address1.add(rs.getString(7)); 
			address2.add(rs.getString(8)); 
			city.add(rs.getString(9)); 
			state.add(rs.getString(10)); 
			zip.add(rs.getString(11));   
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

public ArrayList<String> getName(){
	return name; 
}

public ArrayList<String> getClientType(){
	return clientType; 
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
