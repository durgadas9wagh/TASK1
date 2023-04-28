package test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {

	public static Connection provideConnection() {

		Connection conn=null;
		String url="jdbc:oracle:thin:@localhost:1521:orcl";

		try {
			conn= DriverManager.getConnection(url,"c##durgadas","punisher");
		
		
		} catch (SQLException e) {	e.printStackTrace(); }


		return conn;

	}



}