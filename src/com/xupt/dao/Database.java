package com.xupt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mec.util.core.ConfigReader;

public class Database {
	private static Connection connection;
	private static ResultSet resultSet;
	private static PreparedStatement preStatement;
	
	static {
		try {
			ConfigReader.init("/com/xupt/ymm/xml/user.properties");
			Class.forName(ConfigReader.getValue("bagName"));
			connection = (Connection) DriverManager.getConnection(
						ConfigReader.getValue("loction"), 
						ConfigReader.getValue("root"), 
						ConfigReader.getValue("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet det(String sqlString){
		try {
			preStatement = connection.prepareStatement(sqlString);
			
			resultSet = preStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	//得到列数
	public static String[] getColumnNames() {
		String[] columnNames = null;
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int length = rsmd.getColumnCount();
			columnNames = new String[length];
			for (int i = 0 ; i < length; i++) {
				columnNames[i] = rsmd.getColumnName(i + 1);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return columnNames;	
	}
	
	//查询
	public static List<Map<String, String>> doSelect(String sqlString) {
		ResultSet resultSet = Database.det(sqlString);
		List<Map<String, String>> listSelect = new ArrayList<>();
		String[] columnNames = Database.getColumnNames();
		try {
			while(resultSet.next()) {
				Map<String, String> mapSelect = new HashMap<>();
				for(int i = 0;i < columnNames.length; i++) {
					String key = columnNames[i];
					String value = resultSet.getString(key);
					mapSelect.put(key, value);
				}
				listSelect.add(mapSelect);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listSelect;
	}
	
	//执行
	public boolean execute(String sql) throws SQLException {
		preStatement = connection.prepareStatement(sql);
		
		resultSet = preStatement.executeQuery();
		
		return preStatement.execute();
	}
	
	//查询
	public ResultSet doQuery() throws SQLException {
		return preStatement.executeQuery();
	}
	
	//更新
	
	public static int doUpdate(String str) throws SQLException {
				
		return preStatement.executeUpdate();
	}
	
	public static Boolean add(String str) throws SQLException{
		try {
		preStatement = connection.prepareStatement(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preStatement.execute();
		
	}
	
	public  void close(){
		if(resultSet!=null){
			try {
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		if(resultSet==null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}

