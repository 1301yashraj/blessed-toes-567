package raj.yash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import raj.yash.Utility.ConnectTODB;
import raj.yash.Utility.Login_Signup;
import raj.yash.bean.Item;

public class Seller {
	 int sellerId = 0;
     
     //constructor
     public Seller(int id)
     {
   	  this.sellerId = id;
     }
     
     
	public void checkoutInventory() {
		try(Connection con = ConnectTODB.connect())
		{
			 String stat = "select i.itemId,i.name,i.price,i.quantity"
 		               +",i.category"
  	 		       +",s.sellerid,s.username"
 		               +" from itemsbySellers i"
 		               +" INNER JOIN registeredSellers s"
 		               +" ON i.soldBy = s.sellerId ";
		     PreparedStatement ps = con.prepareStatement(stat
		     		+ "AND soldBy = ? ");
		     ps.setInt(1, this.sellerId);
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
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	
	}
	
	public void addItem() 
	{
		Scanner sc = new Scanner(System.in);
		try(Connection con = ConnectTODB.connect())
		{
			int flag = 1;
			while(flag==1)
			{
				System.out.println("**************************\n");
				System.out.print("Item Name");
				String name = sc.nextLine();
				System.out.print("Item Price");
				int price = sc.nextInt();
				System.out.print("Item Quantity");
				int quan = sc.nextInt();
				int soldBy = this.sellerId;
				sc.nextLine();
				System.out.print("Item Category");
				String category = sc.nextLine();
				
			    PreparedStatement ps = con.prepareStatement("insert into itemsbysellers"
			    		+ "(name,price,quantity,soldBy,category) values (?,?,?,?,?)");
			    ps.setString(1, name);
			    ps.setInt(2,price);
			    ps.setInt(3,quan);
			    ps.setInt(4, soldBy);
			    ps.setString(5,category);
				
			    int x = ps.executeUpdate();
			    if(x>0)
			    	System.out.println("ITEM INSERTED SUCCESFULLY");
			    else
			    	System.out.println("ERROR IN INSERTION");
				System.out.println("press 1 to keep on adding and 0 to exit ");
			    flag = sc.nextInt();
			    sc.nextLine();
			}
			
		}
	   catch(SQLException e) 
		{ 
		   System.out.println(e.getMessage());
		}
	    
	}

	
	public void update() {
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		while(flag)
		{
		  this.checkoutInventory();	
		  System.out.println("Enter ITem ID that you would like to update\n"
		  		+ "0.Exit");
		  int itemid = sc.nextInt();
		  if(itemid==0)
			  flag = false;
		  if(flag) 
		  {
			  Item item = new Item(itemid);
			  System.out.println("What would you like to update"
					              +"\n 1.Name"
					              +"\n 2.Price"
					              +"\n 3.Quantity"
					              +"\n 4.Category"
					              +"\n 5.Exit");
			  int op = sc.nextInt();
			  switch(op) 
			  {
			  case 1 : item.change("Name"); break;
			  case 2 : item.change("Price"); break;
			  case 3 : item.change("Quantity"); break;
			  case 4 : item.change("Category"); break;
			  case 5 : flag = false;
			  }
		  }
		  
		}
	}

    public void remove(){
	Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		while(flag)
		{
		  this.checkoutInventory();	
		  System.out.println("Enter ITem ID that you would like to DELETE\n"
		  		+ "0.Exit");
		  int itemid = sc.nextInt();
		  if(itemid==0)
			  flag = false;
		  if(flag) 
		  {
			  Item item = new Item(itemid);
			  item.delete();
		  }
		}  
    }

    public void soldItems() {
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
    				+ " i.soldBy = seller.sellerId"
    				+ " AND sellerId = "+this.sellerId;
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

