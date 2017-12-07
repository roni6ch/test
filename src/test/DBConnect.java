package test;

import java.sql.*;

public class DBConnect {

	String myDriver = "com.mysql.jdbc.Driver";
	String myUrl = "jdbc:mysql://localhost:3306/servlettest?useSSL=false";

	String userName = null;
	String password = null;
	Connection conn  = null;
	Statement stmt = null;

	//constructor
	public DBConnect(String name, String pass){
		userName = name;
		password = pass;
	}

	//connection method
	public Connection connect() throws ClassNotFoundException, SQLException{
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myUrl, userName, password);
		stmt = conn.createStatement();
		return conn;
	}

	//close connection
	public void closeConnection() throws SQLException{
		stmt.close();
		conn.close();
	}

	public Statement getStatement(){
		return stmt;
	}
	public Connection getConnection(){
		return conn;
	}

}
