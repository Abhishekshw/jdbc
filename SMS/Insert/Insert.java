package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Main.Helper;

public class Insert {
	int phone, s_regno, fee;// veriable decleration
	String sname, branch, grade;
	Scanner S = new Scanner(System.in);// Sacnner object

	public void insert() throws SQLException {// method name

		System.out.println("enrter S_regno");// Asking value from user
		s_regno = S.nextInt();// Storing values
		System.out.println("enrter Sname");
		sname = S.next();
		System.out.println("enrter phone");
		phone = S.nextInt();
		System.out.println("enrter branch");
		branch = S.next();
		System.out.println("enrter fee");
		fee = S.nextInt();
		System.out.println("enrter geade");
		grade = S.next();
		Connection conn = Helper.con();// connection done from helper ckass

		PreparedStatement stmt = conn.prepareStatement("insert into clg_st_details values(?,?,?,?,?,?)");
		// inserting in table
		stmt.setInt(1, s_regno);
		stmt.setString(2, sname);
		stmt.setInt(3, phone);
		stmt.setString(4, branch);
		stmt.setInt(5, fee);
		stmt.setString(6, grade);
		stmt.executeUpdate();// executing the values

	}
}
