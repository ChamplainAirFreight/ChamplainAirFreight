package edu.cco.ChamplainAirFreight.Database;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: DBAddUser - class to interact with the database and the GUI page to insert a new User 
 * using the stored procedure Add_User.
 *
 */

//Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddUser extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public String username;
	public String password;
	
/**
* Default Constructor
* Matt Ridgway 
* 11/12/2020
*/

public DBAddUser(String user,String pass) {
	try {
		this.username=user;
		this.password=pass;
		
		String storedP = "{call CAFDB.dbo.Add_Aircraft}"; 
		callable = connection.prepareCall(storedP);
		insertSQL(username, password);			
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddUser.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New User"); 
	}
	
	
}//end Constructor
/**
* insertSQL Method 
* Matt Ridgway 
* 11/12/2020
* Database structure:
* 1 UserName string
* 2 Password string
*  
*/
public void insertSQL(String user, String pass) {
	try {
		String sql = "{call CAFDB.dbo.Add_User(?,?)}";
		callable=connection.prepareCall(sql);
		callable.setString(1,  user);
		callable.setString(2,  pass);	
		
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert User Problem !");
    }
	
	
}
}
