package edu.cco.ChamplainAirFreight.Database.User;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan	
 * @Date: Nov 29, 2020
 * @Description: DBValidateUserPassword - database based class that uses the ValidateUserPassword stored procedure
 * to validate a user's password from the database for login
 *
 */

public class DBValidateUserPassword extends DBConnection {
	public CallableStatement callable = null;
	private int userID;
	private String userPassword;
	private int answer;
	
	public DBValidateUserPassword() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteUser"); 
		}
	}
	
	public boolean validateUser(int uID, String uPass) {
		try {
			String method = "{call CAFDB.dbo.Delete_User(?,?)}"; 
			userID = uID;
			userPassword = uPass;
			callable = connection.prepareCall(method); 
			callable.setInt(1, userID);	
			callable.setString(2, userPassword);
			callable.registerOutParameter(3, answer);
			callable.executeQuery();
			
			if(answer == 1) {
				return true;
			}else {
				return false;
			}
						
		}catch (SQLException ex) {
			Logger.getLogger(DBValidateUserPassword.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("validate user could not be completed"); 
			return false;
		}
	}
}
