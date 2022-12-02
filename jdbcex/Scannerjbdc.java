package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Scannerjbdc {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("S_id");
		String S_id = s.next();

		System.out.println("enter Sname");
		String s_name = s.next();
		System.out.println("enter phone");
		String phone_no = s.next();
		System.out.println("enter add");
		String s__Add = s.next();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// registering my sql driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Abhidb", "root", "root");// connection
			// Statement stmt = con.createStatement();
			PreparedStatement stmt;
			String sql = "insert into student_mange values(?,?,?,?)";
			// prepareStatement
			stmt = con.prepareStatement(sql);
			stmt.setString(1, S_id);
			stmt.setString(2, s_name);
			stmt.setString(3, phone_no);
			stmt.setString(4, s__Add);
			stmt.execute();
			System.out.println("insert done");
			// ResultSet rs = stmt.executeQuery("Select * from student_mange");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
