package sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

import file.PathConfig;

public class SqlConnect {
	private static Connection conn = null;
	
	public static Connection getConnection(){
		
		String urlString = PathConfig.SQLURL;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(urlString);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(){
		
		try {
			if (conn != null || !conn.isClosed())
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
