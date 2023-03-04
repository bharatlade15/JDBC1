package com.jspiders.jdbcselectdemo.crud;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcCrud {
	private static Connection connection;
	private static java.sql.Statement statement;
	private static int result;
	private static FileReader fileReader;
	private static Properties properties;
	private static String filePath="C:\\WEJA1\\JDBCSelectDemo\\resources\\db_info.properties";
    private static ResultSet resultSet;
	private static String query;
	private static String query2;
	private static String query3;
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			//Read the file
			fileReader=new FileReader(filePath);
			properties=new Properties();
			properties.load(fileReader);
			
			//Load Driver class
			Class.forName(properties.getProperty("driverPath"));
			//open connection
			connection=DriverManager.getConnection(properties.getProperty("dburl"), properties);
			//Create statement
			statement=connection.createStatement();	
			
			// CHECK THE TABLE IS ALREADY PRESESNT OR NOT
			DatabaseMetaData dbMetaData=connection.getMetaData();
			ResultSet resultSet=dbMetaData.getTables(null, null, "emp", null);
			
			// IF TABLE IS ALREADY IS PRESENT THEN IF BLOCK EXECUTE
			if (resultSet.next()) {
				System.out.println("------------TABLE IS ALREADY PRESENT-----------");
				
				
			
           //Execute statement
		   
			// Truncate the table
				query="truncate table emp";
				result=statement.executeUpdate(query);
			//INSERT THE RECORD
			query="insert into emp values"+"(1,'Suraj','Software Developer',900000)"+"," 
                                              + "(2,'Rahul','Test Engineer',100000)"+"," 
					                          + "(3,'Sandeep','Trainee ',600000)"+"," 
                                               +"(4,'Kiran','API Tester',1200000)"+"," 
					                           +"(5,'Bharat','SQL Developer',650000)";
           //Execute result
		     result=statement.executeUpdate(query);
		    System.out.println("-----RECORD INSERTED-----");
		     System.out.println("Query ok "+result+" rows affected");
		    
		     
		     
		    // FETCH THE RECORD
		     query2="select * from emp";
		     resultSet=statement.executeQuery(query2);
		     
		     System.out.println("-----RECORD-----");
		    while(resultSet.next()) {	
		     System.out.println(resultSet.getString(1)+"||"
		    		 +resultSet.getString(2)+"||"
		    		 +resultSet.getString(3)+"||"
		    		 +resultSet.getString(4)+"||");
		     
		    }
		    
		    //UPDATE THE RECORD
		    System.out.println("-----UPDATE THE RECORD-----");
		    for(int i=0;i<=3;i++){
		    	if (i==1) {	
		    query3="update emp"+" set ename = 'Harsh'"+"where empno="+i;
		    result=statement.executeUpdate(query3);
		    System.out.println("Query ok "+result+" rows affected");
		    	}else if (i==3) {
		    		query3="update emp"+" set ename = ' Tushar '"+"where empno="+i;	
		    		result=statement.executeUpdate(query3);
		    		System.out.println("Query ok "+result+" rows affected");
				}
		    }
		    // UPDATED FETCH THE RECORD
		    query2="select * from emp";
		    resultSet=statement.executeQuery(query2);
		    System.out.println("-----UPDATED RECORD-----");
		     while(resultSet.next()) {
		    	 
			     System.out.println(resultSet.getString(1)+"||"
			    		 +resultSet.getString(2)+"||"
			    		 +resultSet.getString(3)+"||"
			    		 +resultSet.getString(4)+"||");
			    }
			
		     //DELETE THE RECORD
		     System.out.println("-----DELETE RECORD-----");
		     for(int i=0;i<6;i++) {
		    	 if(i==2) {
		    	  query="delete from  emp"+" where empno="+i;
		         result=statement.executeUpdate(query);
		         System.out.println("Query ok "+result+" rows affected");
		     } else if (i==5) {
		    	 query="delete from  emp"+" where empno="+i;
		    	 result=statement.executeUpdate(query);
		    	 System.out.println("Query ok "+result+" rows affected");
		     }
				
			}
		     query2="select * from emp";
			    resultSet=statement.executeQuery(query2);
		     System.out.println("-----UPDATED RECORD------");
		     while(resultSet.next()) {
		    	 
			     System.out.println(resultSet.getString(1)+"||"
			    		 +resultSet.getString(2)+"||"
			    		 +resultSet.getString(3)+"||"
			    		 +resultSet.getString(4)+"||");
			    }
		     
		     
			}else {
               // IF TABLE IS ALREADY NOT PRESENT THEN IT WILL CREATE A TABLE FIRST THEN INSERT THE RECORD
			    query="create table emp(empno int PRIMARY KEY,ename varchar(20),job varchar(20),sal LONGTEXT)";
			    result=statement.executeUpdate(query);
			    System.out.println("------------- CREATE TABLE 'emp'---------------");
			    System.out.println("Table is created successfully "+result+" rows affected");
			    
			  //INSERT THE RECORD
				query="insert into emp values"+"(1,'Suraj','Software Developer',900000)"+"," 
	                                              + "(2,'Rahul','Test Engineer',100000)"+"," 
						                          + "(3,'Sandeep','Trainee ',600000)"+"," 
	                                               +"(4,'Kiran','API Tester',1200000)"+"," 
						                           +"(5,'Bharat','SQL Developer',650000)";
	           //Execute result
			     result=statement.executeUpdate(query);
			    System.out.println("-----RECORD INSERTED-----");
			     System.out.println("Query ok "+result+" rows affected");
			    
			     
			     
			    // FETCH THE RECORD
			     query2="select * from emp";
			     resultSet=statement.executeQuery(query2);
			     
			     System.out.println("-----RECORD-----");
			    while(resultSet.next()) {	
			     System.out.println(resultSet.getString(1)+"||"
			    		 +resultSet.getString(2)+"||"
			    		 +resultSet.getString(3)+"||"
			    		 +resultSet.getString(4)+"||");
			     
			    }
			    
			    //UPDATE THE RECORD
			    System.out.println("-----UPDATE THE RECORD-----");
			    for(int i=0;i<=3;i++){
			    	if (i==1) {	
			    query3="update emp"+" set ename = ' Harsh '"+"where empno="+i;
			    result=statement.executeUpdate(query3);
			    System.out.println("Query ok "+result+" rows affected");
			    	}else if (i==3) {
			    		query3="update emp"+" set ename = ' Tushar '"+"where empno="+i;	
			    		result=statement.executeUpdate(query3);
			    		System.out.println("Query ok "+result+" rows affected");
					}
			    }
			    // UPDATED FETCH THE RECORD
			    query2="select * from emp";
			    resultSet=statement.executeQuery(query2);
			    System.out.println("-----UPDATED RECORD-----");
			     while(resultSet.next()) {
			    	 
				     System.out.println(resultSet.getString(1)+"||"
				    		 +resultSet.getString(2)+"||"
				    		 +resultSet.getString(3)+"||"
				    		 +resultSet.getString(4)+"||");
				    }
				
			     //DELETE THE RECORD
			     System.out.println("-----DELETE RECORD-----");
			     for(int i=0;i<6;i++) {
			    	 if(i==2) {
			    	  query="delete from  emp"+" where empno="+i;
			         result=statement.executeUpdate(query);
			         System.out.println("Query ok "+result+" rows affected");
			     } else if (i==5) {
			    	 query="delete from  emp"+" where empno="+i;
			    	 result=statement.executeUpdate(query);
			    	 System.out.println("Query ok "+result+" rows affected");
			     }
					
				}
			     query2="select * from emp";
				    resultSet=statement.executeQuery(query2);
			     System.out.println("-----UPDATED RECORD------");
			     while(resultSet.next()) {
			    	 
				     System.out.println(resultSet.getString(1)+"||"
				    		 +resultSet.getString(2)+"||"
				    		 +resultSet.getString(3)+"||"
				    		 +resultSet.getString(4)+"||");
				    }
			}

			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if (connection!=null)
				connection.close();
				
				if (statement!=null) {
					statement.close();
				}
				
				if (fileReader!=null) {
					fileReader.close();
					
				}
				if (resultSet!=null) {
					resultSet.close();
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}	
			
		}
		
		}

}
