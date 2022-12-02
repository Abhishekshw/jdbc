package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import clg_man.clg_man;

public class Helper {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// registering my sql driver
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection con() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/abhidb", "root", "root");

	}

	public static void main(String... args) throws Exception {

		/*
		 * MenuDrivrneg mde = new MenuDrivrneg(); mde.Savemenudrive(); mde.menuDriven();
		 * mde.updateMenudiven(); mde.deleteMenuDrive();
		 */
		clg_man cg = new clg_man();
		cg.insert();
		cg.view();

	}
}
