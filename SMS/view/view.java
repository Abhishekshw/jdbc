package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Helper;

public class view {
	public void view() throws SQLException {
		Connection conn = Helper.con();// connection from database
		Statement stmt = conn.createStatement();
		// exicuting fetch data from database
		ResultSet rs = stmt.executeQuery("Select * from clg_st_details");
		// itteration
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " "
					+ rs.getInt(5) + " " + rs.getString(6));
		}
	}
}
