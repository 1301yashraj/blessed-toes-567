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
    public void dailySales() 
    {
    	try(Connection con = ConnectTODB.connect())
    	{
    		String stat = "select distinct i.itemid , i.name,i.price,i.category,\r\n"
    				+ " sold.quantity,owner.username Owner,seller.username SoldBy from\r\n"
    				+ " itemssold sold\r\n"
    				+ " INNER JOIN itemsbysellers i\r\n"
    				+ " INNER JOIN registeredBuyers owner\r\n"
    				+ " INNER JOIN RegisteredSellers seller\r\n"
    				+ " ON  sold.itemId = i.ItemId\r\n"
    				+ " AND\r\n"
    				+ " sold.ownerId = owner.buyerId\r\n"
    				+ " AND\r\n"
    				+ " i.soldBy = seller.sellerId";
    		PreparedStatement ps = con.prepareStatement(stat);
    		ResultSet rs = ps.executeQuery();
    		boolean isEmpty = true;
    		while(rs.next()) 
    		{
    			if(isEmpty) 
    			{
    	System.out.println("ID----NAME----PRICE----CATEGORY----QUANTITY----OWNER----SELLER");
    	System.out.println("______________________________________________________________");
    			}
    			isEmpty = false;
    			int id  = rs.getInt("i.itemid");
    			String name = rs.getString("i.name");
    			int price = rs.getInt("i.price");
    			String cat = rs.getString("i.category");
    			int quan= rs.getInt("sold.quantity");
        		String owner = rs.getString("owner");
        		String seller = rs.getString("SoldBy");
  System.out.println(id+"----"+name+"----"+price+"----"+cat+"----"+quan+"----"+owner+"----"+seller);      
  System.out.println("_____________________________________________________________________________________");  		
    		}
    		if(isEmpty)
    		{
    			System.out.println("No sales Made Today");
    		}	
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    }

   }
