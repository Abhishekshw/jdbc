package bms;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

	static int in;

	public static void menu() {
		System.out.println("press 1 for create Account");
		System.out.println("press 2 for deposit");
		System.out.println("press 3 for withdraw");
		System.out.println("press 4 for show balance");
		System.out.println("press 5 for pin change");
		System.out.println("press 6 for close account");
		System.out.println(" press 7 for exit");
	}

	public static void main(String[] args) throws SQLException {
		Banking b = new Banking();
		Scanner S = new Scanner(System.in);// Scanner obj

		// giving option to user

		do {
			menu();
			in = S.nextInt();// taking input

			switch (in) {// using switch case we will do menu draw
			case 1:
				b.createAcc();
				break;
			case 2:
				b.deposite();
				break;
			case 3:
				b.Withdraw();
				break;
			case 4:
				b.Showbal();
				break;
			case 5:
				b.pinchange();
				break;
			case 6:
				b.closeAc();
				break;
			case 7:
				break;
			default:
				System.out.println("wrong input");
			}

		} while (in != 7);
		System.out.println("thank you visit again");
	}
}
