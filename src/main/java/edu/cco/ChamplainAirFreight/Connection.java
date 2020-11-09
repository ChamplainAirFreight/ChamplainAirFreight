package edu.cco.ChamplainAirFreight;
/*
* Connection 
* Author: Matt Ridgway
* Date Created: 11/9/2020
* Date Modified: 11/9/2020
* Modification Notes: matt.ridgway@mymail.champlain.edu
* Description: 
* The connection to the database Class.
* Creates the connection to the database.
*/ 


import java.util.*;
import java.sql.*;


public class Connection {

	/** Code found at:
	 *  https://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
	 * @param args
	 */
	   final String DRIVER = "com.sql.jdbc.Driver";  
	   final static String URL = "jdbc:sqlserver://champlainairfreightdb.cdkdj7bimhhz.us-east-1.rds.amazonaws.com";
	   final static String USER = "CAFProgram";
	   final static String PASS = "CAF2020";
	   
	public static void main(String[] args) {
		String url ="jdbc:sqlserver://champlainairfreightdb.cdkdj7bimhhz.us-east-1.rds.amazonaws.com:"
				+ "1433;"
				+ "databaseName=ChamplainAirFreight;"
				+ "user=CAFProgram;"
				+ "password=CAF2020";		
	//or
		Connection con=null;
		Statement sqlStatment=null;
		//Simple sql statement to show results of the pilot table
		String sql = "SELECT FirstName, LastName FROM Pilots";
		try {
			Class.forName("com.sql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connection good ");
			sqlStatment=((java.sql.Connection) con).createStatement();
			ResultSet result=sqlStatment.executeQuery(sql);	
			
			while(result.next()) {
				//table information to pull and loop through
			   String fName=result.getString("FirstName");
			   String lName=result.getString("LastName");
			   
			   System.out.print("First name "+fName+" Last name "+lName);
			   System.out.print("From Pilots table:/n");
			}
			result.close();
			sqlStatment.close();
			((Statement) con).close();
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(sqlStatment!=null)
		            sqlStatment.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(con!=null)
		            ((Statement) con).close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		
		
		
		   }//end try
	}//end main
}//end class
