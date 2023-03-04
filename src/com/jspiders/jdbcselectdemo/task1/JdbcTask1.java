package com.jspiders.jdbcselectdemo.task1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTask1 {
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	private static int result;
	private static FileReader fileReader;
	private static Properties properties;
	private static String filePath="C:\\WEJA1\\JDBCSelectDemo\\resources\\db_info.properties";
	
	public static int i;
    public static String queryCreate;
	public static String queryInsert;
	public static String querySelect;
	public static String queryUpdate;
	public static String queryDelete;
	public static void main(String[] args) {
		try {
			fileReader=new FileReader(filePath);
			properties=new Properties();
			properties.load(fileReader);
			
			Class.forName(properties.getProperty("driverPath"));
			connection=DriverManager.getConnection(properties.getProperty("dburl"),properties);
			statement=connection.createStatement();
			
			//1.Create emp table
			queryCreate="create table emp(empno int(4) primary key,ename varchar(100),job varchar(100),sal decimal(15,2))";
			result=statement.executeUpdate(queryCreate);
			System.out.println("Query OK, "+result+" row(s) affected.");
			
			//2.Insert 5 records
			System.out.println("----------------------");
			for( i=1;i<=5;i++) {
				queryInsert="insert into emp values"+"("+i+",'Amar','developer',30000.00)";
				result=statement.executeUpdate(queryInsert);
				System.out.println("Query OK, "+result+" row(s) affected.");
			}
			
			//3.Display all records
			System.out.println("--------------------");
			querySelect="select * from emp";
			resultSet=statement.executeQuery(querySelect);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1)+"||"+resultSet.getString(2)+"||"+resultSet.getString(3)+"||"+resultSet.getString(4));
				
			}
			
			//4.Update 2 records
			System.out.println("--------------------");
			queryUpdate="update emp set ename='Birbal' where empno=2";
			result=statement.executeUpdate(queryUpdate);
			System.out.println("Query OK, "+result+" row(s) affected.");
			queryUpdate="update emp set ename='Chinmay' where empno=4";
			result=statement.executeUpdate(queryUpdate);
			System.out.println("Query OK, "+result+" row(s) affected.");
			
			//5.Display all records
			System.out.println("--------------------");
			resultSet=statement.executeQuery(querySelect);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1)+"||"+resultSet.getString(2)+"||"+resultSet.getString(3)+"||"+resultSet.getString(4));
				
			}
			
			//6.Delete 1 record
			System.out.println("--------------------");
			queryDelete="delete from emp where empno=5";
			result=statement.executeUpdate(queryDelete);
			System.out.println("Query OK, "+result+" row(s) affected.");
			
			//7.Display all records
			System.out.println("--------------------");
			resultSet=statement.executeQuery(querySelect);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1)+"||"+resultSet.getString(2)+"||"+resultSet.getString(3)+"||"+resultSet.getString(4));
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (fileReader != null) {
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
