package edu.cco.ChamplainAirFreight.Database.Client;

import java.sql.CallableStatement;
import java.sql.SQLException;

import edu.cco.ChamplainAirFreight.Database.DBConnection;

/**
 * 
 * @author Andrew Dockan
 * @Date Dec 11, 2020
 * @Description - class that relates client type to the client type id. uses View_All_Client_Type in AWS DB
 * @TEST_STATUS - not tested
 */

public class DBViewAllClientType extends DBConnection {
	
	public CallableStatement callable = null; 
	private int clientTypeId;
	private String clientType;
	
	public DBViewAllClientType() {
		try {
			statement = connection.createStatement(); 
			
		}catch(SQLException ex) {
			System.out.println("Database connection failed DBViewAllClientType"); 
		}
		
	}

}
