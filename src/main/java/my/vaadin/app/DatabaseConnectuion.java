package my.vaadin.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectuion {

	   static final String DB_URL = "jdbc:mysql://localhost/VaadinDB";
	   static final String USER = "root";
	   static final String PASS = "root";
	   private Connection conn = null;
	   private Statement stmt = null;
	   ResultSet resultSet = null;

	private static DatabaseConnectuion databaseInstance;
	   
	   private  DatabaseConnectuion(){
	      try {
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    stmt = conn.createStatement();
		    resultSet = stmt.executeQuery("SELECT * FROM Customers");
		
		   
		} catch (Exception e) {
			e.printStackTrace();
		} 
	   }
	   
	   public ResultSet getResultSet() {
		   ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery("SELECT * FROM Customers");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	   } 
	
	public static DatabaseConnectuion getInstance() {
		if (databaseInstance == null) {
			databaseInstance = new DatabaseConnectuion();
		}
		return databaseInstance;
	}
	
	public ResultSet delete(){
	    try {
			resultSet = stmt.executeQuery("SELECT * FROM Customers");
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	    return resultSet;
	}
}
