package raj.yash.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			 String stat = "select i.itemId,i.name,i.price,i.quantity"
 		               +",i.category"
  	 		           +",s.sellerid,s.username"
 		               +" from itemsbySellers i"
 		               +" INNER JOIN registeredSellers s"
 		               +" ON i.soldBy = s.sellerId ";
		     PreparedStatement ps = con.prepareStatement(stat);
		     
		     ResultSet rs = ps.executeQuery();
		     boolean hasNoItems = true; 
		     while(rs.next()) 
		     {
		    	 hasNoItems = false;
		    	 int id = rs.getInt("i.itemId");
		    	 String name = rs.getString("i.name");
		    	 int price = rs.getInt("i.price");
		    	 int quan = rs.getInt("i.quantity");
		    	 int sellerId = rs.getInt("s.sellerid");
		    	 String sellerName = rs.getString("s.username");
		    	 String category = rs.getString("i.category");
		    	 Item item = new Item(id,name,price,quan,sellerId,sellerName,category);
		    	 System.out.println(item);
		    	 
		     }
		     if(hasNoItems) 
		     {
		    	 System.out.println("Empty Inventory !!!!!");
		     }
		     else {
		      this.buy();  
		     }
		     
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public  void buy() 
	{ 
		Scanner sc = new Scanner(System.in);
		try(Connection con = ConnectTODB.connect()) 
		{
			boolean flagbuy = true;
			while(flagbuy) 
			{
				System.out.println("Enter Item ID you want to perchase");
				System.out.println("0.Exit");
			    int press =sc.nextInt();
			    if(press==0)
			    	break;
			    else 
			    {
			    	String stat = "select i.itemId,i.name,i.price,i.quantity"
		 		               +",i.category"
		  	 		           +",s.sellerid,s.username"
		 		               +" from itemsbySellers i"
		 		               +" INNER JOIN registeredSellers s"
		 		               +" ON i.soldBy = s.sellerId AND i.itemId="+press;
			    	PreparedStatement ps= con.prepareStatement(stat);
			    	ResultSet rs = ps.executeQuery();
			    	while(rs.next())
			    	{
			    		 int id = rs.getInt("i.itemId");
				    	 String name = rs.getString("i.name");
				    	 int price = rs.getInt("i.price");
				    	 int quan = rs.getInt("i.quantity");
				    	 int sellerId = rs.getInt("s.sellerid");
				    	 String sellerName = rs.getString("s.username");
				    	 String category = rs.getString("i.category");
				    	 System.out.println("________________________________");
				    	 System.out.println(
									"ItemID :: "+ id+"\n"
				                    +"Product Name :: "+ name+"\n"
				                    +"Price :: "+ price+"\n"
				                    +"Quantity :: "+quan+"\n"
				                    +"Seller :: "+ sellerName+"\n"
				                    +"Category :: "+ category+"\n" 
								   );
				    	 boolean cancle = true;
				    	 int q=0 ; 
				    	 while(cancle)
				    	 {
				    		 System.out.println("Enter Quantity or Enter 0 to Exit");
					    	  q = sc.nextInt();
					    	  if(q>quan && q !=0) {
					    		  System.out.println("Invalid");
					    	  }
					    	  if(q==0||q<=quan)
					    		  cancle = false;
				    	 }	 
				    	 if(q<=quan && q!=0)
				    	 {
				    		 int val = quan-q;
				    		String str = "Update itemsbySellers set quantity = "+val+
				    				" where itemid = "+id;
				    		PreparedStatement ps2 = con.prepareStatement(str);
				    		int x = ps2.executeUpdate();
				    		if(x>0)
				    		{
				    			System.out.println("Item Purchase By you");      
				    		    PreparedStatement sold = con.prepareStatement
				    				   ("insert into itemsSold values (?,?,?,?)");
				    		   sold.setInt(1,id);
				    		   sold.setInt(2,this.buyerId);
				    		   sold.setInt(3,q);
				    		   java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				    		    sold.setTimestamp(4, date);
				    		   int z = sold.executeUpdate();
				    		   if(z>0)
				    		   {
				    			   System.out.println("Added in you purchased ITEMS list");
				    		   }   
				    		   else
				    			   System.out.println("Unable to purchase");
				    		}	
				    		else
				    		{
				    			System.out.println("Unknown Error");
				    		}
				    	 } 
				    	 
			    	}
			    }
			    
			}
		}
		catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		
		
	}

}
