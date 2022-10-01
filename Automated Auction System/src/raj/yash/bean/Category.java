package raj.yash.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import raj.yash.Utility.ConnectTODB;
import raj.yash.dao.Buyer;

public class Category {
	
   public List<String> category  = new ArrayList<>();

@Override
    public String toString() {
	String ans = "";
	int i=0;
	for(String item:category) {
		i++;
		ans += "\n "+i+". "+item; 
	}
	return ans;
}

public void checkoutCategory(int buyerId) {
	Scanner sc = new Scanner(System.in);
	try(Connection con = ConnectTODB.connect())
	{
		boolean cflag = true;
		while(cflag) 
		{
			 Category cat = this;
		     System.out.println(cat);
		     int mychoice = sc.nextInt();
		     mychoice--;
		     if(mychoice == cat.category.size()-1)
		    	break;
		     else 
		     {
		    	 String select = cat.category.get(mychoice);
		    	 System.out.println("You have choosed "+select);
		    	 String stat = "select i.itemId,i.name,i.price,i.quantity"
		   		               +",i.category"
		    	 		       +",s.sellerId,s.username"
		   		               +" from itemsbySellers i"
		   		               +" INNER JOIN registeredSellers s"
		   		               +" ON i.soldBy = s.sellerId ";
		         PreparedStatement ps = con.prepareStatement(stat+"AND"
		         		+ " category = ?");
		         ps.setString(1, select);
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
		         Buyer buyer = new Buyer(buyerId);
	        	 buyer.buy();
		     }
		     
		}
	}
	catch(Exception e)
	{
	    System.out.println(e.getMessage());	
	}
	
	
}
   
   
}
