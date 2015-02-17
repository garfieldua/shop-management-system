package com.naukma.shop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class MySQLProvider extends AbstractDataProvider {
	
	private Connection con;
	private static MySQLProvider instance = null;
	
	public static final String URL = "";
	public static final String USER = "";
	public static final String PASSWORD = "";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private MySQLProvider() {
		try {
			Class.forName(DRIVER_CLASS);
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static MySQLProvider getInstance() {
		if (instance == null) {
			instance = new MySQLProvider();
		}
		return instance;
	}

	public ResultSet execute(String query) {
		try {
			Statement s = con.createStatement();
			
			s.execute(query);
			ResultSet rs = s.getResultSet();

			return rs;
		}

		catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		return null;
	}
}
