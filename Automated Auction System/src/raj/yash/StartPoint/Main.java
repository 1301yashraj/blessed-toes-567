package raj.yash.StartPoint;

import java.util.Scanner;


import raj.yash.usecase.AdminOptions;
import raj.yash.usecase.BuyerOptions;
import raj.yash.usecase.SellerOptions;

public class Main {

	public static void main(String[] args) {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while(run) 
		{
			System.out.println("Welcome Enter Your Position :"
	        		+ "\n 1.Admin"
	        		+ "\n 2.Buyer"
	        		+ "\n 3.Seller" 
	        		+ "\n 4.Exit");
	        int n = sc.nextInt();
	        switch(n)
	        {
	           case 1 : AdminOptions.start();break;
	           case 2 : BuyerOptions.start();break;
	           case 3 : SellerOptions.start();break;
	           case 4 : run = false;;
	        }
		}
		System.out.println();
        System.out.println("----********THANK-YOU********----");
	}

}
