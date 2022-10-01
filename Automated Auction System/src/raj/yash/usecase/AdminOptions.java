package raj.yash.usecase;

import java.util.Scanner;

import raj.yash.Utility.Login_Signup;
import raj.yash.dao.Admin;

public class AdminOptions  {
	public static void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("YOU can login >>>");
	    System.out.println("===================");
        System.out.println("Enter Username :");
	      String username = sc.next();
	      System.out.println("Enter PASSWORD :");
	      String password = sc.next();
		int isAdmin = Login_Signup.login("Admins",username,password);
		boolean authorized = false;
		while(isAdmin>0) {
			authorized = true;
			System.out.println("Hello ,"+"\n");
			System.out.println("------------MENU------------");
			System.out.println("\n1.Checkout ALL Buyers"
					           +"\n2.Checkout ALL Sellers"
					           +"\n3.Checkout Daily Sales"
					           +"\n4.Logout(Admin)");
		  int n = sc.nextInt();
		  Admin admin = new Admin(isAdmin);
		  switch(n) {
		  case 1 : admin.allTable("Buyer"); break;
		  case 2 : admin.allTable("Seller"); break;
		  case 3 : admin.dailySales(); break;
		// WE should not call the main method it can lead to errors, 
		// just for now Later we will change it.
		  case 4 :isAdmin= -1;  
		  }
			
		}
		if(!authorized){
			System.out.println("You are not Authorized as Admin");
		}
	}

}
