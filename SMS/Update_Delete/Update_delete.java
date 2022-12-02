package Update_Delete;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update_delete {
	Scanner s = new Scanner(System.in);

	public void update() throws SQLException {
		Connection conn = Main.Helper.con();// connection from helper class
		Statement stmt = conn.createStatement();
		System.out.println("what you want to update? ");// asking from user what he want to upadate
		System.out.println("press 1 for student r_regno ");
		System.out.println("press 2 for student name ");
		System.out.println("press 3 for phone number ");
		System.out.println("press 4 for branch ");
		System.out.println("press 5 for fee ");
		System.out.println("press 6 for grade ");
		int in = s.nextInt();// input from user
		switch (in) {// giving option to by switch case
		case 1: {
			System.out.println("enter new registraion number");
			int nreg = s.nextInt();
			System.out.println("enter old registraion number");
			int oreg = s.nextInt();
			stmt.executeUpdate("update clg_st_details set S_regno='" + nreg + "' where S_regno=" + oreg);// update to
																											// database
			break;
		}
		case 2: {
			System.out.println("enter reg no");
			int reg = s.nextInt();
			System.out.println("enter Student name");
			String Sname = s.next();
			stmt.executeUpdate("update clg_st_details set Sname='" + Sname + "' where S_regno=" + reg);// update to
																										// database
			break;
		}
		case 3: {
			System.out.println("enter reg no");
			int reg = s.nextInt();
			System.out.println("enter Student mobile number");
			int phone = s.nextInt();
			stmt.executeUpdate("update clg_st_details set phone='" + phone + "' where S_regno=" + reg);// update to
																										// database
			break;
		}
		case 4: {
			System.out.println("enter reg no");
			int reg = s.nextInt();
			System.out.println("enter Student branch");
			String branch = s.next();
			stmt.executeUpdate("update clg_st_details set branch='" + branch + "' where S_regno=" + reg);// update to
																											// database
			break;
		}
		case 5: {
			System.out.println("enter reg no");
			int reg = s.nextInt();
			System.out.println("enter Student fees");
			int fee = s.nextInt();
			stmt.executeUpdate("update clg_st_details set fee='" + fee + "' where S_regno=" + reg);// update to database
			break;
		}
		case 6: {
			System.out.println("enter reg no");
			int reg = s.nextInt();
			System.out.println("enter Student grade");
			String grade = s.next();
			stmt.executeUpdate("update clg_st_details set grade='" + grade + "' where S_regno=" + reg);// update to
																										// database
			break;
		}
		}
	}

	public void delete() throws SQLException {
		Scanner s = new Scanner(System.in);
		Connection conn = Main.Helper.con();// getting connection from helper class
		Statement stmt = conn.createStatement();
		System.out.println("Enter S_regno : ");
		int S_regno = s.nextInt();
		stmt.executeUpdate("delete from clg_st_details where S_regno=" + S_regno);// delete from database

	}
}
