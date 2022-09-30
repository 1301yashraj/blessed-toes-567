package raj.yash.usecase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import raj.yash.Utility.ConnectTODB;
import raj.yash.Utility.Login_Signup;
import raj.yash.dao.Buyer;
import raj.yash.dao.Seller;

public class SellerOptions {
	public static void start() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		String tablename = "RegisteredSellers";
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
		              SellerOptions.functions(bs);	 
		             }
		             else
		             {
		            	 System.out.println("Not a user\nUSername "
		            	 		+ "or paswword Incorrect");
		             }
		              break;
			        }
				   
			case 3 : flag = false;         
			}
		}
	
	}
	
	public static void functions(int sellerId)
	{
	   try(Connection con = ConnectTODB.connect())
	   {
		   Scanner sc = new Scanner(System.in);
		   boolean flag = true;
		   while(flag)
		   {
			   System.out.println("What would you like to do :: \n"
				   		+ "1.Checkout Inventory \n"
						  +"2.Add Item \n"
						  +"3.Update \n"
						  +"4.Delete Item \n"
						  +"5.View Sold Items"
						  +"6.Exit");
				   int n = sc.nextInt();
				   Buyer buyer = new Buyer(sellerId);
				   switch(n)
				   {
				    case 1 : buyer.seeAll();break;
				    case 2: buyer.addItem(); break;
				    case 3 : buyer.update(); break; 
				   }  
		   }
		   
	   }
	   catch(SQLException e) 
	   {
		   e.getMessage();
	   }
	}

}
