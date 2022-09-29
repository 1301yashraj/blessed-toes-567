package raj.yash.bean;

import java.util.Scanner;
import raj.yash.Utility.Login_Signup;

public class Admin extends Login_Signup{

	public static void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("YOU can login >>>");
		System.out.println("===================");
		System.out.println("Enter Username :");
		String username = sc.next();
		System.out.println("Enter PASSWORD :");
		String password = sc.next();
		boolean isAdmin = Login_Signup.login("Admins", username, password);
		if(isAdmin) {
			System.out.println("HelLo "+username);
			System.out.println("What ");
		}
		else {
			System.out.println("You are not Authorized as Admin");
		}
	}

}
