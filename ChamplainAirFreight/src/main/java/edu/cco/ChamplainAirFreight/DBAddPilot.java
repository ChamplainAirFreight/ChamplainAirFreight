package edu.cco.ChamplainAirFreight;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: DBAddPilot - class to interact with the database and the GUI page to insert a new pilot
 * using the stored procedure Add_Pilot.
 */

// Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddPilot extends DBConnection{
	//Variables
	public CallableStatement callable = null;
	public String pFirstName;
	public String pLastName;
	public Date pDOB;
	public String pNumber;
	public Date pHireDate;
	
	
/**
* Default Constructor
* Matt Ridgway 
* 11/11/2020
*/
public DBAddPilot(String fName, String lName, Date dob, String eNumber,Date hire) {
			try {
				this.pFirstName=fName;
				this.pLastName=lName;
				this.pDOB=dob;
				this.pNumber=eNumber;
				this.pHireDate=hire;
				
				String storedP = "{call CAFDB.dbo.Add_Pilot}"; 
				callable = connection.prepareCall(storedP);
				insertSQL(pFirstName, pLastName, pDOB, pNumber, pHireDate);			
			}
			catch (SQLException ex) {
				Logger.getLogger(DBAddPilot.class.getName()).log(Level.SEVERE, null, ex);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Problem adding New Pilot"); 
			}
			
}//End Default Constructor
	
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
 * 1 PilotID int
* 2 first name string
* 3 last name string
* 4 DOB date
* 5 employee number string
* 6 date of hire date
* 7 date leftCAF date
*
*/
public void insertSQL(String fName, String lName, Date dob, String eNumber,Date hire){
		try {
			String sql = "{call CAFDB.dbo.Add_Pilot(?,?,?,?,?)}";
			callable=connection.prepareCall(sql);
			callable.setString(1,  fName);
			callable.setString(2, lName);
			callable.setDate(3, dob);
			callable.setString(4, eNumber);
			callable.setDate(5,hire);
			
			//Execute Stored Procedure
			callable.executeQuery();
			
		} catch (SQLException ex) {
	        System.out.println("Insert Client Problem !");
	    }
		
	}//end insertSQL


}
