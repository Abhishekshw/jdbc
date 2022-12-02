package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Insert.Insert;
import Update_Delete.Update_delete;
import view.view;

public class Helper {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// registering my sql driver
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection con() throws SQLException {// connecting with server
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/abhidb", "root", "root");

	}

	public static void main(String... args) throws Exception {// main method
		Scanner s = new Scanner(System.in);

		Insert n = new Insert();// object of inser view ,uodatew delete class
		view v = new view();
		Update_delete ud = new Update_delete();
		System.out.println("enter 1 for insert");
		System.out.println("enter 2 for view");
		System.out.println("enter 3 for update");
		System.out.println("enter 4 for delete");
		int in = s.nextInt();
		switch (in) {// by using switch case we have created option for user
		case 1:
			n.insert();
			break;
		case 2:
			v.view();
			break;
		case 3:
			ud.update();
			break;
		case 4:
			ud.delete();
			break;

		}
		System.out.println("thank you");
	}
}
