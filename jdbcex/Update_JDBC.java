package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Update_JDBC {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// registering my sql driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Abhidb", "root", "root");// connection
			Statement stmt = con.createStatement();
			// updating and executing records
			stmt.executeUpdate("update student_mange set s__Add='WestBengal' where S_id=2");
			stmt.executeUpdate("delete from student_mange where S_id=5");
			ResultSet rs = stmt.executeQuery("select * from student_mange");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
