package bms;

//importing reqired pakage
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class InvalidPassWord extends Exception {// creating coustom exception invalid password
	InvalidPassWord(String m) {
		super(m);
	}
}

class InvalidAcno extends Exception {// creating coustom exception Ac is in valid
	InvalidAcno(String m) {
		super(m);
	}
}

class Insufficientbal extends Exception {// creating coustom exception insufficent balance
	Insufficientbal(String m) {
		super(m);
	}
}

class Acnoexist extends Exception {// creating coustom exception ac no already created
	Acnoexist(String msg) {
		super(msg);
	}
}

public class Banking {// banking class here all method avaiable
	static String AcType;// declearing datatype and verable
	static int oldAc, verifypin, Cbal, count;

	public static void createAcc() throws SQLException {

		Scanner S = new Scanner(System.in);// scanner obj
		System.out.println("enter your Ac no");
		int Ac_no = S.nextInt();// user iunput
		System.out.println("enter your name");
		String Name = S.next();
		String ifcscode = "Sbin00089";// ifsc is giver by application /mannually
		String branch = "sewli";
		System.out.println("chose your AC-type" + "Enter 1 for Savings" + " Enter 2 for Commercial");// giving option
																										// for user

		int ac_type = S.nextInt();
		if (ac_type == 1) {
			AcType = "Savings";
		}
		if (ac_type == 2) {
			AcType = "Commercial";
		}
		System.out.println("Enter balance for Acc open");
		int balance = S.nextInt();
		System.out.println("enter youe pin" + " kindly note it not more than 4 digt");
		int pin = S.nextInt();

		Connection conn = Helper.con();// connection done from helper class
		Statement stmt1 = conn.createStatement();// create Statment
		ResultSet rs = stmt1.executeQuery("Select * from bank");
		try {
			while (rs.next()) {
				oldAc = (rs.getInt(1));
				if (Ac_no == oldAc) {
					throw new Acnoexist(" Ac no already exist try again");
				}
			}
			PreparedStatement stmt = conn.prepareStatement("insert into bank values(?,?,?,?,?,?,?)");// taking input
			// creating account // from user
			stmt.setInt(1, Ac_no);
			stmt.setString(2, Name);
			stmt.setString(3, ifcscode);
			stmt.setString(4, branch);
			stmt.setString(5, AcType);
			stmt.setInt(6, balance);
			stmt.setInt(7, pin);
			stmt.executeUpdate();
			System.out.println("ac open  ");
		} catch (Exception e) {
			System.out.println(e);// printing Exception
		}

	}

	public static void deposite() throws SQLException {
		count = 0;
		Scanner S = new Scanner(System.in);
		System.out.println("enter Ac no");
		int Ac_no = S.nextInt();
		System.out.println("enter your pin");
		int pin = S.nextInt();
		System.out.println("enter deposite Amount");
		int depos = S.nextInt();
		Connection conn = Helper.con();// gettingg connection by helper
		Statement stmt1 = conn.createStatement();
		ResultSet rs2 = stmt1.executeQuery("Select * from bank");// taking result from database
		try {
			// cheking id is avaible or not
			while (rs2.next()) {
				oldAc = (rs2.getInt(1));
				if (Ac_no == oldAc) {
					count++;
				}
				if (count == 0) {// if id is not avaible its will throw an error
					System.out.println(count);
					throw new InvalidAcno("invalid ac no try again");
				}
			}
			Statement stmt = conn.createStatement();
			// exicuting fetch pin from database
			ResultSet rs = stmt.executeQuery("Select pin from bank where account_no=" + Ac_no);
			while (rs.next()) {
				verifypin = (rs.getInt(1));// getting pin of perticular user
			}
			ResultSet rs1 = stmt.executeQuery("Select balance from bank where account_no=" + Ac_no);// getting balance
																									// from sql
			while (rs1.next()) {
				Cbal = (rs1.getInt(1));
			}
			int totalbal = (Cbal + depos);
			if (verifypin == pin) {// verifing pin from database
				stmt.executeUpdate("update bank set balance='  " + totalbal + "  'where account_no=" + Ac_no);// updating
																												// account
																												// balance
			} else {// if password willnot match it will throw an error
				throw new InvalidPassWord("you have entered wrong password try again");
			}
			System.out.println("your total balance is " + totalbal);// updated balance
		} catch (Exception e) {
			System.out.println(e);// printing exception
		}
	}

	public static void Withdraw() throws SQLException {
		count = 0;
		Scanner S = new Scanner(System.in);
		System.out.println("enter Ac no");
		int Ac_no = S.nextInt();// Scanner input from user
		System.out.println("enter your pin");
		int pin = S.nextInt();
		System.out.println("enter withdraw Amount");
		int withdraw = S.nextInt();
		Connection conn = Helper.con();// getting connection by helper class
		Statement stmt1 = conn.createStatement();
		ResultSet rs2 = stmt1.executeQuery("Select * from bank");// fecting datr from data base
		try {

			Statement stmt = conn.createStatement();
			// exicuting fetch data from database
			Statement stmt2 = conn.createStatement();
			ResultSet rs3 = stmt2.executeQuery("Select * from bank");

			while (rs3.next()) {
				oldAc = (rs3.getInt(1));
				if (Ac_no == oldAc) {// creating coustom exception
					count++;

				}
				if (count == 0) {
					throw new InvalidAcno("invalid ac no try again");// if not match it will trow an error
				}
			}
			ResultSet rs = stmt.executeQuery("Select pin from bank where account_no=" + Ac_no);
			while (rs.next()) {
				verifypin = (rs.getInt(1));// verifing password from data base
			}
			ResultSet rs1 = stmt.executeQuery("Select balance from bank where account_no=" + Ac_no);
			while (rs1.next()) {
				Cbal = (rs1.getInt(1));// Storing current balance
			}
			int totalbal = (Cbal - withdraw);// balance after withdraw
			if (Cbal < withdraw) {
				throw new Insufficientbal(" Insufficient balance for withdraw try with lower amount");
			} else {
				if (pin == verifypin) {// verifing password
					stmt.executeUpdate("update bank set balance='  " + totalbal + "  'where account_no=" + Ac_no);// upadting
																													// in
																													// database
				} else {// if not verified it will throw an error
					throw new InvalidPassWord(" entered wrong pin try again");
				}
			}
			System.out.println("your updated balance is " + totalbal);
		} catch (Exception e) {
			System.out.println(e);// printing Exception
		}
	}

	public static void Showbal() throws SQLException {

		Scanner S = new Scanner(System.in);
		System.out.println("enter Ac no");
		int Ac_no = S.nextInt();
		System.out.println("enter your pin");
		int pin = S.nextInt();
		Connection conn = Helper.con();// getting connection by helper class
		Statement stmt1 = conn.createStatement();
		ResultSet rs2 = stmt1.executeQuery("Select * from bank");// fecting database
		try {
			while (rs2.next()) {
				oldAc = (rs2.getInt(1));
				if (Ac_no == oldAc) {// cheking account no is avaiable in data base or not
					count++;
				}
				if (count == 0) {// if account no is not with database it will generate an exception
					throw new InvalidAcno("invalid ac no");
				}
			}
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select pin from bank where account_no=" + Ac_no);
			while (rs.next()) {
				verifypin = (rs.getInt(1));// getting pin froM DATABASE
			}
			if (pin == verifypin) {// verifing pin froM DATABASE
				ResultSet rs1 = stmt.executeQuery("Select balance from bank where account_no=" + Ac_no);// getting
																										// balance from
																										// database
				while (rs1.next()) {
					System.out.println("your balanxce is " + rs1.getInt(1));// output for viewe balance
				}
			} else {// if password not match it will throw exception
				throw new InvalidPassWord("you have entered invalid  password try again");
			}
		} catch (

		Exception e) {
			System.out.println(e);// printing Exception msg
		}

	}

	public static void closeAc() throws SQLException {
		count = 0;
		Scanner S = new Scanner(System.in);
		System.out.println("enter Ac no");
		int Ac_no = S.nextInt();
		System.out.println("enter your pin");
		int pin = S.nextInt();
		Connection conn = Helper.con();// getting connection by helper class
		Statement stmt = conn.createStatement();
		Statement stmt1 = conn.createStatement();
		ResultSet rs2 = stmt1.executeQuery("Select * from bank");// fecting data
		try {

			while (rs2.next()) {
				oldAc = (rs2.getInt(1));
				if (Ac_no == oldAc) {// cheking account no is avaible in database or not
					count++;
				}
				if (count == 0) {// acc no is not avaible in then it will throw exception
					throw new InvalidAcno("invalid ac no try again");
				}
			}
			ResultSet rs = stmt.executeQuery("Select pin from bank where account_no=" + Ac_no);
			while (rs.next()) {
				verifypin = (rs.getInt(1));// Storing password from database
			}
			if (pin == verifypin) {// verifing password
				stmt.executeUpdate("delete from bank where account_no=" + Ac_no);// deleting record
				System.out.println("Ac closed ");
			} else {// if password not match it will throw an exception
				throw new InvalidPassWord("you have entered invalid  password try again");
			}

		} catch (Exception e) {
			System.out.println(e);// printing Error msg
		}
	}

	public static void pinchange() throws SQLException {
		count = 0;
		Scanner S = new Scanner(System.in);
		System.out.println("enter Ac no");
		int Ac_no = S.nextInt();
		System.out.println("enter your pin");
		int pin = S.nextInt();
		System.out.println("enter new pin ");
		int Npin = S.nextInt();// Assking user for values
		Connection conn = Helper.con();// getting connection from helper class
		Statement stmt1 = conn.createStatement();
		ResultSet rs2 = stmt1.executeQuery("Select * from bank");
		try {
			while (rs2.next()) {
				oldAc = (rs2.getInt(1));
				if (Ac_no == oldAc) {// cheking ac no is avaible or not
					count++;
					System.out.println(count);
				}
				if (count == 0) { // if acno is not avaiable the throw ann error
					throw new InvalidAcno("invalid ac no try again");
				}
			}
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select pin from bank where account_no=" + Ac_no);
			while (rs.next()) {
				verifypin = (rs.getInt(1));// getting pin by database
			}
			if (pin == verifypin) {// verifing pin
				stmt.executeUpdate("update bank set pin='  " + Npin + "  'where account_no=" + Ac_no);// updating pin
				System.out.println("pin changed ");
			} else {
				throw new InvalidPassWord("you have entered invalid  password try again");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
