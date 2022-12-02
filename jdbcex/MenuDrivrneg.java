package com.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuDrivrneg {
	// public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	static int e_id, e_age, e_salary;
	static String e_name, e_city;

	public void Savemenudrive() throws Exception {
		System.out.print("enter emp id");
		e_id = s.nextInt();
		System.out.print("enter name");
		e_name = s.next();
		System.out.print("enter age");
		e_age = s.nextInt();
		System.out.println("Enter city");
		e_city = s.next();
		System.out.println("Enter salary");
		e_salary = s.nextInt();
		Connection conn = Helper.con();// helper class is already created wehave called the menthod
//preparedStatement
		PreparedStatement stmt = conn.prepareStatement("insert into menudriven values(?,?,?,?,?)");
		stmt.setInt(1, e_id);
		stmt.setString(2, e_name);
		stmt.setInt(3, e_age);
		stmt.setString(4, e_city);
		stmt.setInt(5, e_salary);
		stmt.executeUpdate();
	}
//feching emp details in database (Display)

	public void menuDriven() throws SQLException {
		Connection conn = Helper.con();
		Statement stmt = conn.createStatement();
		// exicuting fetch data from database
		ResultSet rs = stmt.executeQuery("Select * from menudriven");
		// itteration
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " "
					+ rs.getInt(5));
		}
	}

	// uploading emp details in database
	public void updateMenudiven() throws SQLException {
		Connection conn = Helper.con();
		Statement stmt = conn.createStatement();
		System.out.println("enter emp city");
		e_city = s.next();

		stmt.executeUpdate("update menudriven set e_city='  " + e_city + "  'where e_id=" + "e_id");
		System.out.println("update done");
	}

	// deleting emp details in the database
	public void deleteMenuDrive() throws SQLException {
		Connection conn = Helper.con();
		Statement stmt = conn.createStatement();
		System.out.println("enter emp id");
		e_id = s.nextInt();
		stmt.executeUpdate("delete from menudriven where e_id= +e_id");

	}

}
