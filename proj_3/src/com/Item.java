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
	
	/*Insert Item to the Database*/

}
