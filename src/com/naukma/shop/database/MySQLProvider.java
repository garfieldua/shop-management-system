package com.naukma.shop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public final class MySQLProvider extends AbstractDataProvider {
	
	private Connection con;
	private static MySQLProvider instance = null;
	
	public static final String URL = "jdbc:mysql://37.140.192.17/u0044137_default";
	public static final String USER = "u0044137_default";
	public static final String PASSWORD = "hTrzP0p~tJB@";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	public MySQLProvider() {
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

	public DaoResult execute(String query) {
		try {
			java.sql.PreparedStatement s = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			s.execute();
			
			ResultSet rs = s.getResultSet();
			if (rs == null) {
				rs = s.getGeneratedKeys();
			}

			return new DaoResult(rs);
		}

		catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		return null;
	}
}
