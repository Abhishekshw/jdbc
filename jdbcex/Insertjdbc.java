package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insertjdbc {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// registering my sql driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Abhidb", "root", "root");// connection
			Statement stmt = con.createStatement(); // statement
			stmt.executeUpdate("insert into student_mange values (1,'DEV',12345678,'Patuli')");// inserting data
			stmt.executeUpdate("insert into student_mange values (2,'ARNAB',09876675,'US')");// inserting data
			stmt.executeUpdate("insert into student_mange values (3,'SANKALPA',1200000,'ARGENTINA')");// inserting data

			System.out.println("inserted successfully");
			con.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
