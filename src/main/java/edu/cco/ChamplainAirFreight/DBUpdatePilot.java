package edu.cco.ChamplainAirFreight;
/**
 * NOT TESTED
 * @author Matt Ridgway
 * @Date: November 21, 2020
 * @Description: DBUpdatePilot - class to interact with the database and the GUI page 
 * to update the Pilot information, using the stored procedure Update_Pilot.
 * @MODDIFIED: 
 */
//Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUpdatePilot extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public ArrayList<Integer> pilotID=new ArrayList<>();
		public String pilotInfo;
		public String firstName;
		public String lastName;
		public Date dateOfBirth;
		public String employeeNum;
		public Date startDate;
		public Date endDate;
		
		private int id;


/**
 * DataBase Structure:
 * 1. PilotID int
 * 2. FirstName String
 * 3. LastName String
 * 4. DateOfBirth Date
 * 5. EmployeeNumber String
 * 6. DateOfHire Date
 * 7. DateLeftCAF Date
* 
* Default Constructor
* Matt Ridgway 
* 11/21/2020
*/
		public DBUpdatePilot() {
			
			try {
				statement=connection.createStatement();
				setPilot();
			}catch (SQLException e) {
			    System.out.println("Could Not Connect to DataBase!");
			}
	}//end default constructor	
/**
* setPilot pulls pilotID put's into a String with comma spacing
* Matt Ridgway 
* 11/21/2020
* */
public void setPilot() {
			
		try {
				String sql="SELECT PilotID FROM Pilots";
				ResultSet result=statement.executeQuery(sql);
				while(result.next()) {
					pilotInfo+=result.getString(1)+", ";
					
			}
				
		}catch (SQLException ex) {
		        Logger.getLogger(DBUpdatePilot.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Could Not View Pilots");
	    }
			
		setPilotArray();
			
}//end setPilot
/**
* setPilotArray
* Matt Ridgway 
* 11/21/2020
* split string pilotInfo by comma into list. Loop through and put into pilotID List
*/	
public void setPilotArray() {
	
	List<String> pilotList=new ArrayList<>(Arrays.asList(pilotInfo.split(",")));
	for(String strg : pilotList) {
		pilotID.add(Integer.valueOf(strg));
		
	}
	
}//end setPilotArray
/**
* clearPilot
* Clear List and String
* Matt Ridgway 
* 11/21/2020
*/	
public void clearPilot() {
	
	pilotID.clear();
	pilotInfo="";
}//end clearPilot
/**
* setPilotSQL
* 
* Matt Ridgway 
* 11/21/2020
* @param pilotSelected
*/
public void setPilotSQL(int pilotSelected) {
	
	try {
	String sql="SELECT PilotID, FirstName, LastName, DateOfBirth, EmployeeNumber,DateOfHire,DateLeftCAF "
			+ "FROM Pilots Where PilotID=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, pilotSelected);
    ResultSet resultS = preparedStatement.executeQuery();
	
    	while(resultS.next()) {
    		id=resultS.getInt(1);
    		firstName=resultS.getString(2);
    		lastName=resultS.getString(3);
    		dateOfBirth=resultS.getDate(4);
    		employeeNum=resultS.getString(5);
    		startDate=resultS.getDate(6);
    		endDate=resultS.getDate(7);
    		
    	}
	} catch (SQLException ex) {
        Logger.getLogger(DBUpdatePilot.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Get Pilot Problem!");
    }
	
}//end setPilotSQL
/** RESULTSET
* updatePilot
* 
* Matt Ridgway 
* 11/20/2020*
* @param fName
* @param lName
* @param dob
* @param empNum
* @param pStartDate
* @param pEndDate
* 
*/
public ResultSet updatePilot(String fName, String lName,Date dob, String empNum, Date pStartDate,Date pEndDate,  int input) {
	 ResultSet results = null;
	 try {
		 String sqlState="SELECT PilotID FROM Pilots WHERE PilotID=?";
		 PreparedStatement preparedStatement;
     preparedStatement = connection.prepareStatement(sqlState);
     preparedStatement.setInt(1, input);
     ResultSet resultS = preparedStatement.executeQuery();
    while (resultS.next()) {
        id = resultS.getInt(1);
    }
	}catch (SQLException ex) {
   Logger.getLogger(DBUpdatePilot.class.getName()).log(Level.SEVERE, null, ex);
}
	 try {
    String SQL = "UPDATE Pilots SET "
    		+ "FirstName=?,"
    		+ "LastName=?,"
    		+ "DateOfBirth=?,"
    		+ "EmployeeNumber=?,"
    		+ "DateOfHire=?,"
    		+ "DateLeftCAF=?";
    	
    callable = connection.prepareCall(SQL);
    callable.setString(2,fName);
    callable.setString(3, lName);
    callable.setDate(4,dob);
    callable.setString(5, empNum);
    callable.setDate(6, pStartDate);
    callable.setDate(7, pEndDate);
    
    results = callable.executeQuery();

    System.out.println(results);

} catch (SQLException ex) {
    System.out.println("Results Not Returned");
}

return results;
}//end ResultSet
/**
* updatePilot
* Matt Ridgway 
* 11/21/2020
*/
public void updatePilot() {
    try {
    	String storedP = "{call CAFDB.dbo.Update_Pilot}"; 
        PreparedStatement ps;
        ps = connection.prepareStatement(storedP);
        callable = connection.prepareCall(storedP);

        ResultSet rs = callable.executeQuery(); 

    } catch (SQLException ex) {
        System.out.println("Update Pilot Problem!");
    }
}//end updatePilot
/**
* Getters
* Matt Ridgway 
* 11/21/2020
*/
public ArrayList<Integer> getPilotID() {
	return pilotID;
}
public String getPilotInfo() {
	return pilotInfo;
}
public String getFirstName() {
	return firstName;
}
public String getLastName() {
	return lastName;
}
public Date getDateOfBirth() {
	return dateOfBirth;
}
public String getEmployeeNum() {
	return employeeNum;
}
public Date getStartDate() {
	return startDate;
}
public Date getEndDate() {
	return endDate;
}
public int getId() {
	return id;
}
}
