package com;

import java.sql.*;

public class Item {
	
	/*Testing Database connection*/
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab3_paf",
	 "root", "1234");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	/*End of Testing Database connection*/
	
	/*Method to Insert Item to the Database*/
	
	public String insertItem(String code, String name, String price, String desc)
	{
		
		String output = "";
		
		try
		{
			
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database";
			}
	
			String query = " insert into items(`itemId`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)" + " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
	
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
	
	
			preparedStmt.execute();
			
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	/*End of Method */
	
	

}
