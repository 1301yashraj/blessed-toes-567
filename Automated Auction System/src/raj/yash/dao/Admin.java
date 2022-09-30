package raj.yash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import raj.yash.StartPoint.Main;
import raj.yash.Utility.ConnectTODB;
import raj.yash.Utility.Login_Signup;

public class Admin{
      int adminId = 0;
      
      //constructor
      public Admin(int id)
      {
    	  this.adminId = id;
      }
	
	public void allTable(String person) {
		String personid = person+"id";
		String tablename = "Registered"+person+"s";
		try(Connection con = ConnectTODB.connect()){
			
		    PreparedStatement ps = con.prepareStatement("select "
		    		+ personid+",username"
				+ " from "+tablename);
		    ResultSet rs = ps.executeQuery();
		    boolean isEmpty =  true;
		    while(rs.next())
		    {   int id = 0;
		        String name = null; 
		    	if(isEmpty)
		    	{
		    	  System.out.println("All Registerd "+person);
				  
				  System.out.println("ID"+" --- "+"Username");
				  System.out.println("------------------------");
		    	}
		    	
		      isEmpty = false;
		      id = rs.getInt(personid);
			  name = rs.getString("Username");
		      System.out.println(id+" --- "+name);
		    }
		    System.out.println("_____________________");
		    if(isEmpty)
		    	 System.out.println("No "+person+" Registered yet");
		    
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}


   }
