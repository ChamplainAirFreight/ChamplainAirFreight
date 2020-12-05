package edu.cco.ChamplainAirFreight.Database.User;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
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
	private String userName;
	private String userPassword;
	private int answer;
	
	public DBValidateUserPassword() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBDeleteUser"); 
		}
	}
	
	public boolean validateUser(String uName, String uPass) {
		
		try {
			String method = "{call CAFDB.dbo.Validate_User_Password(?,?,?)}"; 
			userName = uName;
			userPassword = uPass;
			callable = connection.prepareCall(method); 
			callable.setString(1, userName);	
			callable.setString(2, userPassword);
			callable.registerOutParameter(3, Types.NUMERIC);
			callable.execute();
			answer = callable.getInt(3);
			
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
