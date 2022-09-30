package raj.yash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import raj.yash.Utility.ConnectTODB;
import raj.yash.bean.Item;


public class Buyer  {
   
	 int buyerId = 0;
     
     //constructor
     public Buyer(int id)
     {
   	  this.buyerId = id;
     }

	public void seeAll() {
		try(Connection con = ConnectTODB.connect())
		{
		     PreparedStatement ps = con.prepareStatement("select * from  "
		     		+ "itemsbysellers ");
		     
		     ResultSet rs = ps.executeQuery();
		     boolean hasNoItems = true; 
		     while(rs.next()) 
		     {
		    	 hasNoItems = false;
		    	 int id = rs.getInt("itemId");
		    	 String name = rs.getString("name");
		    	 int price = rs.getInt("price");
		    	 int quan = rs.getInt("quantity");
		    	 int soldBy = rs.getInt("soldBy");
		    	 String category = rs.getString("category");
		    	 Item item = new Item(id,name,price,quan,soldBy,category);
		    	 System.out.println(item);
		     }
		     if(hasNoItems) 
		     {
		    	 System.out.println("Empty Inventory !!!!!");
		     }
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
