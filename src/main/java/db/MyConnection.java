package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 public class MyConnection {
	static String url="jdbc:mysql://localhost:3306/file_hider";
	public static Connection connection=null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection=DriverManager.getConnection(url,"root","Akshay@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("success");
		return connection;
		
		
	}
	public static void closeConnection() {
		if (connection !=null) {
			try {
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	public static void main(String[] args) {
//		MyConnection.getConnection();
	}
 }





