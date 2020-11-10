package edu.cco.ChamplainAirFreight;
//imports:
import java.util.*; 
import java.util.logging.Logger; 
import java.util.logging.Level; 
import java.sql.*; 

/**
 * DBConnection - Parent class that houses the connection to the AWS microsoft sql server database
 * All classes that require a db connection will use "extends DBConnection" in their class name. 
 * @author Matt Ridgway and Kelly May
 *  *date: 11/9/2020
 */
public class DBConnection {
	final String DRIVER = "com.sql.jdbc.Driver";  
	   final static String URL = "jdbc:sqlserver://champlainairfreightdb.cdkdj7bimhhz.us-east-1.rds.amazonaws.com";
	   final static String USER = "CAFProgram";
	   final static String PASS = "CAF2020";
	   protected Connection connection; 
	   protected Statement statement; 
	   
	   /**
	    * Default Constructor - connects database
	    */
	   public DBConnection() {
		   try {
			   System.out.println("Driver Loaded"); 
			   //establish the connection
			   connection = DriverManager.getConnection(URL, USER, PASS); 
			   System.out.println("Connected to AWS Microsoft SQL Server."); 
		   } catch(SQLException ex) {
			   Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
			   System.out.println("DB Couldnot be connected"); 
		   }
	   }
	   
	   
	   /**
	    * closeConnection - closes the SQL connection
	    */
	   public void closeConnection() {
		   try {		   connection.close(); 
	   } catch(SQLException ex) {
		   Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		   System.out.println("DB could not be disconnected"); 
	   }
	   }  

} //end class
