package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static DBManager instance;
	public static final String DB_NAME = "KnifeShop";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
	private static final String DB_USER = "root";
	private static final String DB_PASS = "61996188";
	
	private Connection con;
	
	private DBManager(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		}catch(SQLException | ClassNotFoundException e){
			System.out.println("Error connecting to DB : " +  e.getMessage());
		}
	}
	
	public static synchronized DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void closeConnection(){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
}
