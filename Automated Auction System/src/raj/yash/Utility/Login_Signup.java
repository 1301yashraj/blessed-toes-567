package raj.yash.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login_Signup {
   String username = "";
   String password = "";
   
   public static String signup(String tableName) {
	   Scanner sc = new Scanner(System.in);
		System.out.println("YOU can Signup as "+tableName+" >>>");
		System.out.println("===================");
		System.out.println("Enter Username :");
		String username = sc.next();
		System.out.println("Enter PASSWORD :");
		String password = sc.next();	   
		String message =  "Unable to Register user";
	    try(Connection con = ConnectTODB.connect())// we got the connection 
	    {
		// now insert into our table 
		PreparedStatement ps = con.prepareStatement(
				"insert into "+tableName+"(username,password) "
						+ "values (?,?)");
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		int x = ps.executeUpdate();
		if(x>0) {
			message ="You Have been Registerd now you can login ";
		}
		
	}
	catch(SQLException e)
	{
		message = e.getMessage();
	}
	   return message;   
   }

public static int login(String tableName,String username,String password) {
	String colName = null;
	System.out.println(tableName);
	if(tableName.equals("RegisteredSellers"))
		 colName  = "sellerId";
	else 
		{
		  if(tableName.equals("registeredbuyers"))
		  {
			  colName = "buyerId"; 
		  }  
	      else
		  {
	    		colName = "id";
	      }
		}
	
	
	int logSuccess = 0;
	 try(Connection con = ConnectTODB.connect()){
		 PreparedStatement ps = con.prepareStatement("Select * from "
		 		+ ""+tableName+" where username = ? AND password = ? ");
		 ps.setString(1, username);
		 ps.setString(2, password);
		 ResultSet rs = ps.executeQuery();
		 
		 if(rs.next()) {
			 logSuccess = rs.getInt(colName);
		 }
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return logSuccess;
}


}
