package edu.asupoly.ser322.jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/*
This sample program connects to the database at the given URL and makes the specified query.
It takes as parameters the username and password.
 */
public class DeptQuery
{
	public static void main(String[] args)
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;

		if (args.length != 4)
		{
			System.out.println("USAGE: java edu.asupoly.ser322.jdbcex.DeptQuery <url> <user> <passwd> <driver>");
			System.exit(0);
		}
		String _url = args[0];
		try {
			// Step 1: Load the JDBC driver
			Class.forName(args[3]);

			// Step 2: make a connection
			conn = DriverManager.getConnection(_url, args[1], args[2]);

			// Step 3: Create a statement
			stmt = conn.createStatement();

			// Step 4: Make a query
			rs = stmt.executeQuery("Select * from Department");

			// Step 5: Display the results
			while (rs.next()) {
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getInt(2) + "\t ");
				System.out.print(rs.getInt(3) + "\t ");
				System.out.println(rs.getDate(4));
			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		finally {  // ALWAYS clean up your DB resources
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}