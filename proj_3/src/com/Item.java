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
	
	
	/*Method to read Items from the Database*/
	public String readItems()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
	
			
			output = "<table border=‘1’><tr><th>Item Code</th>" +"<th>Item Name</th><th>Item Price</th>"
					+ "<th>Item Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			
			String query = "select * from items";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
	
			while (rs.next())
			{
				
				String itemID = Integer.toString(rs.getInt("itemID"));
				
				String itemCode = rs.getString("itemCode");
				
				String itemName = rs.getString("itemName");
				
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				
				String itemDesc = rs.getString("itemDesc");
				
	
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";
				
	
				output += "<td><form method='post' action='updateItem.jsp'>"
						+ "<input name='btnUpdate' "
						+ " type='submit' value='Update'>"
						+ "<input name='itemID' type='hidden' "
						+ " value=' " + itemID + "'>"
						+ "<input name='itemCode' type='hidden' "
						+ " value=' " + itemCode + "'>"
						+ "<input name='itemName' type='hidden' "
						+ " value=' " + itemName + "'>"
						+ "<input name='itemPrice' type='hidden' "
						+ " value=' " + itemPrice + "'>"
						+ "<input name='itemDesc' type='hidden' "
						+ " value=' " + itemDesc + "'>"
						+ "</form></td>"
						+ "<td><form method='post' action='deleteItem.jsp'>"
						+ "<input name='btnRemove' "
						+ " type='submit' value='Delete'>"
						+ "<input name='itemID' type='hidden' "
						+ " value='" + itemID + "'>" + "</form></td></tr>";
			}
			
			con.close();
			
	
			output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the items.";
		System.err.println(e.getMessage());
	}
		
	return output;
	
	}
	
	/*end of method*/
	
	
	/*Method to delete Items from the Database*/
public String deleteItem(Integer itemCode) {
		
		String output = "";
		
		Connection con = connect();
		
		String sql = "delete from items " + "where itemId=" +itemCode ;
		
		try{
			
			PreparedStatement preparedStmt = con.prepareStatement(sql);

		
			preparedStmt.executeUpdate();
			
			output = "Deleted Successfully!!";
		}
		catch (Exception e) {
			output = "Error while deleting";
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	/*End of method*/

}
