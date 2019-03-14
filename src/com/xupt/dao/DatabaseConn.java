package com.xupt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConn {
	private static Connection connection = null;
	private PreparedStatement preparedStatement;
	
	static {
		initDatabaseConnection();
	}
	
	private static void initDatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/xupt_ymm_data",
					"root",
					"199866");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DatabaseConn(String SQLString) {
		if(connection == null) {
			initDatabaseConnection();
		}
		try {
			preparedStatement = connection.prepareStatement(SQLString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public boolean execute() throws SQLException {
		return preparedStatement.execute();
	}
	
	
	public ResultSet doQuery() throws SQLException {
		return preparedStatement.executeQuery();
	}
	
	
	public int doUpdate() throws SQLException {
		return preparedStatement.executeUpdate();
	}
	
	public void closeConnection() {
		try {
			preparedStatement.close();
			connection.close();
			
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
