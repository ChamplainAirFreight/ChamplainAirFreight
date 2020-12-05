package edu.cco.ChamplainAirFreight.Database.Client;

/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: dbAddClient - class to interact with the database and the GUI page to insert a new client
 * using the stored procedure Add_Client.
 */

// Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

public class DBAddClient extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public String name;
	public int type;
	public String phone;
	
	
/**
* Default Constructor
* Matt Ridgway 
* 11/11/2020
*/
/*public DBAddClient(String clientName, int clientType, String clientPhone) {
		try {
			this.name=clientName;
			this.type=clientType;
			this.phone=clientPhone;
			String storedP = "{call CAFDB.dbo.Add_Client}"; 
			callable = connection.prepareCall(storedP);
			insertSQL(name,type,phone);		
		}
		catch (SQLException ex) {
			Logger.getLogger(DBAddClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Problem adding New Client"); 
		}

}//End Default Constructor
*/

	/**
	 * Default Constructor
	 */
	public DBAddClient() {
		//nothing just call this class. 
	}
	
	
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
 * 1 ClientID int
* 2 ClientName string
* 3 ClientTypeID int
* 4 ClientPhoneNumber string
*
*/
public void insertSQL(String clientName, int clientType, String clientPhone) {
	try {
		String sql = "{call CAFDB.dbo.Add_Client(?,?,?)}";
		callable=connection.prepareCall(sql);
		callable.setString(1,  clientName);
		callable.setInt(2, clientType);
		callable.setString(3, clientPhone);
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert Client Problem !");
    }
	
}//end insertSQL

}
