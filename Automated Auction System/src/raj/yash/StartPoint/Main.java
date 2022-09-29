package raj.yash.StartPoint;

import java.util.Scanner;

import raj.yash.bean.Admin;
import raj.yash.bean.Buyer;
import raj.yash.bean.Seller;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Welcome Enter Your Position :"
        		+ "\n 1. Admin"
        		+"\n 2.Buyer"
        		+"\n 3.Seller");
        int n = sc.nextInt();
        switch(n) {
        case 1 : Admin.start();break;
       // case 2 : Buyer.start();break;
       // case 3 : Seller.start();break;
        }
	}

}
