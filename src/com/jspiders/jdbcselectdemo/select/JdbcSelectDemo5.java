package com.jspiders.jdbcselectdemo.select;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcSelectDemo5 {

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	private static FileReader fileReader;
	private static Properties properties;
	private static String filePath="C:\\WEJA1\\JDBCSelectDemo\\resources\\db_info.properties";
	
	public static void main(String[] args) {
		
		try {
			fileReader=new FileReader(filePath);
			properties=new Properties();
			properties.load(fileReader);
			
			Class.forName(properties.getProperty("driverPath"));
			connection=DriverManager.getConnection(properties.getProperty("dburl"),properties);
			statement=connection.createStatement();
			resultSet=statement.executeQuery(properties.getProperty("query"));
			while (resultSet.next()) {
			 System.out.println(resultSet.getString(2)+"||"+resultSet.getString(3)+"||"+resultSet.getString(4));
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection!=null) {
					connection.close();
				}
				if (statement!=null) {
					statement.close();
				}
				if (resultSet!=null) {
					resultSet.close();
				}
				if (fileReader!=null) {
					fileReader.close();
				}
			} catch (SQLException e) {
			 e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
