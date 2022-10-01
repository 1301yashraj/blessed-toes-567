package raj.yash.usecase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import raj.yash.Utility.ConnectTODB;
import raj.yash.Utility.Login_Signup;
import raj.yash.bean.Category;
import raj.yash.dao.Buyer;

public class BuyerOptions {
	public static void start() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		String tablename = "registeredbuyers";
		while(flag)
		{
			System.out.println("Select >>>>> \n 1.Signup (if new user)  \n"
					+ " 2.Login (if already have an account)"+
					"\n 3.Exit ");
			int n = sc.nextInt();
			switch(n)
			{
			 case 1 : String signup = Login_Signup.signup(tablename);
			          System.out.println(signup);
			          break;
			 case 2 :{
				      System.out.println("YOU can login >>>");
				      System.out.println("===================");
			          System.out.println("Enter Username :");
				      String username = sc.next();
				      System.out.println("Enter PASSWORD :");
				      String password = sc.next();
				   
				     int bs = Login_Signup.login(tablename,username,password);
		             if(bs>0) 
		             {
		              BuyerOptions.functions(bs);	 
		             }
		             else
		             {
		            	 System.out.println("Not a user\nUSername or paswword Incorrect");
		             }
		              break;
			        }
				   
			case 3 : flag = false;         
			}
		}
	
	}
	
	public static void functions(int buyerId)
	{
		BuyerOptions b1 = new BuyerOptions();
	   try(Connection con = ConnectTODB.connect())
	   {
		   Scanner sc = new Scanner(System.in);
		   boolean flagc = true;
		   while(flagc)
		   {
			   System.out.println("What would you like to buy \n"
				   		+ "1.See ALL \n"
						  +"2.See Category Wise \n3.Exit" );
				   int n = sc.nextInt();
				   Buyer buyer = new Buyer(buyerId);
				   switch(n)
				   {
				    case 1 : buyer.seeAll();break;
				    case 2 : b1.seeCategory(buyerId);break;
				    case 3 : flagc = false; 
				   }  
		   }
		   
	   }
	   catch(SQLException e) 
	   {
		   e.getMessage();
	   }
	}
	public  void seeCategory(int buyerId) {
		
		
		try(Connection con = ConnectTODB.connect())
		{
			PreparedStatement ps = con.prepareStatement("select category from "
					+ "itemsbySellers group by category ");
			ResultSet rs = ps.executeQuery();
		    Category cat = new Category();
		    while(rs.next()) 
		    {
		    	String itemcat = rs.getString("category");
		    	cat.category.add(itemcat);
		    }
		    cat.category.add("Exit");
			cat.checkoutCategory(buyerId);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
