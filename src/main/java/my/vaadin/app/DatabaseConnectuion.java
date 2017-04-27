package my.vaadin.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectuion {

	

	   static final String DB_URL = "jdbc:mysql://localhost/mydb";
	   static final String USER = "root";
	   static final String PASS = "root";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	     
	      Class.forName("com.mysql.jdbc.Driver");

	   
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);


	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT name FROM users";
	      ResultSet rs = stmt.executeQuery(sql);

	      while(rs.next()){
		         String name = rs.getString("name");
		         System.out.print("Name: " + name); 

//	         int age = rs.getInt("age");
//	         System.out.print(", Age: " + age);
	    
	      }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	
	      se.printStackTrace();
	   }catch(Exception e){

	      e.printStackTrace();
	   } finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   System.out.println(" Goodbye!");
	}
	
}
