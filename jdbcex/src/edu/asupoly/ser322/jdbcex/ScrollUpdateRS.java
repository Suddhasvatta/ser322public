package edu.asupoly.ser322.jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import edu.asupoly.ser322.Ser322DbUtils;

public class ScrollUpdateRS {
	public static void main(String[] args) {

		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;

		if (args.length != 6) {
			System.out.println("USAGE: java edu.asupoly.ser322.jdbcex.ScrollUpdateRS <url><user><passwd><driver><query><update string>");
			System.exit(0);
		}

		try {
			Class.forName(args[3]);
			conn = DriverManager.getConnection(args[0], args[1], args[2]);

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(args[4]);

			System.out.println("Executed query, let's scroll around...");
			whereAmI(rs);
			System.out.println("next...");
			rs.next();
			whereAmI(rs);
			System.out.println("previous...");
			rs.previous();
			whereAmI(rs);
			System.out.println("last...");
			rs.last();
			whereAmI(rs);
			System.out.println("next...");
			rs.next();
			whereAmI(rs);
			System.out.println("previous...");
			rs.previous();
			whereAmI(rs);
			System.out.println("first...");
			rs.first();
			whereAmI(rs);
			System.out.println("absolute 2...");
			rs.absolute(2);
			whereAmI(rs);
			System.out.println("relative -1...");
			rs.relative(-1);
			whereAmI(rs);

			// now update
			if (!rs.isBeforeFirst() && !rs.isAfterLast()) {
				rs.updateInt(1, Integer.parseInt(args[5]));
				rs.updateRow();
				Ser322DbUtils.printResultSetRow(rs);
			}
		}
		catch (Exception se) {
			se.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}


	public static void whereAmI(ResultSet rs) throws SQLException {
		if (rs.isBeforeFirst()) {
			System.out.println("You are before first");
		}
		else if (rs.isFirst()) {
			System.out.println("You are at the first row");
			// let's output this row
			Ser322DbUtils.printResultSetRow(rs);
		}
		else if (rs.isLast()) {
			System.out.println("You are at the last row");
			// let's output this row
			Ser322DbUtils.printResultSetRow(rs);
		}
		else if (rs.isAfterLast()) {
			System.out.println("You are after the last row");
		}
		else {
			System.out.println("You are so lost dude...");
			// let's output this row
			Ser322DbUtils.printResultSetRow(rs);
		}
	}
}    
