package edu.asupoly.ser322;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

public final class Ser322DbUtils {
	private static Properties __dbProperties;
    
    public static Connection getConnection(String propertyFile) throws Ser322DbWrapperException {	
    		try {
    			__dbProperties = new Properties();
    			__dbProperties.load(Ser322DbUtils.class.getClassLoader().getResourceAsStream(propertyFile));
    			return getConnection();
    		} catch (Throwable t) {
    			System.err.println("Exception initializing from properties file " + propertyFile);
    			throw new Ser322DbWrapperException(t);
    		}
    }
    
	public static Connection getConnection() throws Ser322DbWrapperException
	{
		// assumes info from properties file in previous call
		return getConnection(__dbProperties.getProperty("jdbcUrl"),
							__dbProperties.getProperty("jdbcUser"),
							__dbProperties.getProperty("jdbcPasswd"),
							__dbProperties.getProperty("jdbcDriver"));
	}
	// use this version if the caller passes in the properties
	public static Connection getConnection(String jdbcUser, String jdbcPasswd, String jdbcURL,  String jdbcDriver)
			throws Ser322DbWrapperException
	{
		Properties props = new Properties();
		props.setProperty("user", jdbcUser);
		props.setProperty("password", jdbcPasswd);

		return getConnection(jdbcURL, props, jdbcDriver);
	}
	// use this version if the caller passes in the properties
	public static Connection getConnection(String url, Properties props, String jdbcDriver)
			throws Ser322DbWrapperException
	{
		try {
			Class.forName(jdbcDriver);
			return DriverManager.getConnection(url, props);
		}
		catch (Throwable t) {
			t.printStackTrace();
			throw new Ser322DbWrapperException("Ser322DbUtils::getConnection", t);
		}
	}

	public static void releaseConnection(Connection conn) {
		// presumed fail-safe
		try {
			if (conn != null) {
				conn.close();
			}
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static int printResultSetHeader(ResultSet rs) throws SQLException {
		return printResultSetHeader(rs.getMetaData());
	}

	public static int printResultSetHeader(ResultSetMetaData metaData) throws SQLException
	{
		int numColumns = metaData.getColumnCount();
		for (int i=1; i <= numColumns; i++)
			System.out.print(metaData.getColumnLabel(i) + "\t");
		System.out.println("");
		return numColumns;
	}

	public static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = printResultSetHeader(metaData);

		while (rs.next()) {
			printResultSetRow(rs, numColumns);
		}
	}

	public static void printResultSetRow(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = printResultSetHeader(metaData);

		printResultSetRow(rs, numColumns);
	}

	public static void printResultSetRow(ResultSet rs, int numColumns) throws SQLException {
		Object obj = null;
		for (int i=1; i <= numColumns; i++) {
			obj = rs.getObject(i);
			if (obj != null) {
				System.out.print(rs.getObject(i).toString() + "\t");
			}
			else {
				System.out.print("\t\t");
			}
		}
		System.out.println("");
	}

	private Ser322DbUtils() {}
}
