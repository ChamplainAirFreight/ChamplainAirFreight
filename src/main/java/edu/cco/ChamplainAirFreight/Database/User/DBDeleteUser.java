package edu.cco.ChamplainAirFreight.Database.User;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 19, 2020
 * @Description: DBDeleteUser - database based class that uses the DeleteUser stored procedure
 * to delete a user from the database
 *
 */

public class DBDeleteUser extends DBConnection{
	
	public CallableStatement callable = null;
	private int userID;
	
	public DBDeleteUser() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteUser"); 
		}
	}
	
	public void deleteUser(int uID) {
		try {
			String method = "{call CAFDB.dbo.Delete_User(?)}"; 
			userID = uID;
			callable = connection.prepareCall(method); 
			callable.setInt(1, userID);			
			callable.executeQuery();
						
		}catch (SQLException ex) {
			Logger.getLogger(DBDeleteUser.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete user could not be completed"); 
		}
	}

}
