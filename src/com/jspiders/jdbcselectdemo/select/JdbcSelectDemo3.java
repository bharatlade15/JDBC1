package com.jspiders.jdbcselectdemo.select;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSelectDemo3 {
	public static void main(String[] args) {
		try {
			// 1.Load Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2.Open Connection
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/weja1", "root", "root");
			
			//3.Create/Prepare Statement
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select * from student");
		
		//4.Process Result
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1)+"||"+ resultSet.getString(2));			
		}
		
		//5.Close Connections
		connection.close();
		statement.close();
		resultSet.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
