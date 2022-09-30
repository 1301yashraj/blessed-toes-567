package raj.yash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import raj.yash.Utility.ConnectTODB;


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
		     		+ "itemsby seller where soldBy = ?");
		     ps.setInt(1, this.buyerId);
		     ResultSet rs = ps.executeQuery();
		     while(rs.next()) 
		     {
		    	 System.out.println("");
		     }
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
