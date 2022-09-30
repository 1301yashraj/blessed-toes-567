package raj.yash.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import raj.yash.Utility.ConnectTODB;

public class Item {
    int itemid ;
    String name;
    int price;
    int quantity;
    int soldBy;
    String category;
	public Item(int itemid, String name, int price, int quantity, int soldBy, String category) {
		super();
		this.itemid = itemid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.soldBy = soldBy;
		this.category = category;
	}
	
	public Item(int id) {
		this.itemid  = id;
	}
	
	@Override
	public String toString() {
		return "Item [itemid=" + itemid + " ---  name=" + name + 
				" ---  price=" + price + " ---  quantity=" + quantity +
				" ---  soldBy="+ soldBy + " ---  category=" + category+
				"\n_____________________";
		
	}
	
	public void change(String col) 
	{
		Scanner sc = new Scanner(System.in);
		int n = 0; String val = null;
		if(col.equals("Name"))
		{
			System.out.println("Enter new name");
			val = sc.nextLine();
		}
		else 
			if(col.equals("Price"))
		    {
				System.out.println("Enter new Price");
				n = sc.nextInt();
		    }
			else 
				if(col.equals("Quantity"))
		        {
					System.out.println("Enter new Quantity");
					n = sc.nextInt();
		        }
				else
				{
					System.out.println("Enter new Category");
					val = sc.nextLine();
		        }
			
		try(Connection con = ConnectTODB.connect())
		{
			PreparedStatement ps= null;
			if(n != 0)
			{
				ps   = con.prepareStatement("update itemsbysellers "
						+ "SET "+col+" = "+n+" where itemid = ?");
			}	
			else 
			{
			    ps   = con.prepareStatement("update itemsbysellers "
						+ "SET "+col+"='"+val+"' where itemId = ?");

			}
			ps.setInt(1,this.itemid);
			int x = ps.executeUpdate();
			if(x>0)
				System.out.println("Updated");
			else
				System.out.println("Erro in updation");
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void delete() {
		
		
		try(Connection con = ConnectTODB.connect())
		{
			PreparedStatement ps   = con.prepareStatement("delete from itemsbysellers "
						+ "where itemid = ?");
			ps.setInt(1, this.itemid);
			int x  = ps.executeUpdate();
			if(x>0){
				System.out.println("Record Removed");
			}
			else
				System.out.println("No record found");
		}
		catch(SQLException e)
		{
		System.out.println(e.getMessage());	
		}
	}
}














