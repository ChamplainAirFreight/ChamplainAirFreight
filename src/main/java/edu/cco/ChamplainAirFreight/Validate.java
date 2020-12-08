package edu.cco.ChamplainAirFreight;


import java.util.Scanner;

public class Validate {

	
	/** Blank
	    * Default Constructor 
	    */
	   public Validate() {
		   
	   }
	   //String checker
	   public static boolean isString(String str) {
		    String[] splitString = str.split(" ");
		    boolean string1 = splitString.length == 2;
		    boolean string2= true;
		    for (String s : splitString) {
		        if (s.length() < 2) {
		            string2 = false;
		        }
		    }//end split

		    if (string1 && string2) {//if 1 and 2
		        return true;
		    }
		    return false;
		}
	   //int checker
	   public static int intChecker(String num) {
		   Scanner sc = new Scanner(System.in);
		   int intNumber = 0;
		  
		   try {
			   intNumber = Integer.parseInt(num);
		     
		   } catch (NumberFormatException ex) {
		       //Error not number
		   }
		return intNumber;
		   
	   }
	   //phone number
	   private static boolean checkPhoneNumber(String phoneNumber) {
			//number format
			if (phoneNumber.matches("\\d{10}")) { 
				return true;
			}
			// -, . or spaces format
			else if(phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
				return true;				
			} 
			//3 to 5 extension format
			else if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
				return true;				
			} 
			//area code in brackets format
			else if(phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) { 
				return true;
			}
			//doesn't match
			else return false;
			
		}
	   //zip code 
	   public static boolean zipCodeUS( String s ) {
		      return s.matches( "\\d{5}" );
		   }
	   //email 
	   static boolean isValidEmail(String email) {
		      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		      return email.matches(regex);
		   }

	
	   
}
