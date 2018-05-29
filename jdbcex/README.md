This directory contains some basic command-line executable examples for JDBC processing in Java. The examples are:

* DeptQuery.java - Runs a basic select query against the company database.
* DeptQueryByName.java - Runs a basic select query against the company database and processes the results by column name.
* DeptQueryMetaData.java - Runs a basic select query against the company database and processes results by inspecting the ResultSetMetaData.
* JDBCEx1.java - A generic command-line program for executing a select query against a database.
* DDLjdbcEx1.java - Uses DDL and DML to create a simple table in an empty database (you must create the empty database beforehand) and add a couple of tuples.
* DDLjdbcEx2.java - Intended to be run after DDLjdbcEx1, it drops the table and recreates the database.
* DriverVersion.java - Inspects all drivers in the classpath, loads them into the DriverManager, then selects the proper one based on the JDBC URI given.
* HoldCursorsEx.java - An advanced JDBC example showing code to hold cursors open after a commit as an optimization.
* ScrollUpdateRS.java - An advanced JDBC example showing code to scroll around a ResultSet and also update tuples in a ResultSet.
* TypesExample.java - An advanced JDBC example showing code to map SQL types to Java types in java.sql
* JDBCConnectionMetadata.java - An advanced JDBC example showing code to examine a database based on Connection metadata.

A set of sample database seed scripts are provided in the sql subdirectory. The DeptQuery* examples need the company database, but otherwise the databases are small and generic. They should all be loadable into Postgres, MySQL, and Apache Derby. You may get some warnings but those can be ignored.


