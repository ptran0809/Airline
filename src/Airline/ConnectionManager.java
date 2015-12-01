package Airline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.mysql.jdbc.Statement;

/**
 * The SINGLETON pattern
 * 
 * Using the Singleton pattern, create a unique instance of the inner 
 * class JDBCMySQLConnection.  Since we always need a database connection,
 * create its Connection instance eagerly.
 */
public class ConnectionManager {
	
    private ConnectionManager() {}  // Private constructor!
	
    // This method returns THE connection
    public static Connection getMySQLConnection() {
        return JDBCMySQLConnection.getConnection();
    }

 
   /**
    * This is a private inner class.  Only usable by its owner 
    * ConnectionManager.
    */
	private static class JDBCMySQLConnection {
	
		private static final String URL = "jdbc:mysql://airline.c1d6abjrzty9.us-east-1.rds.amazonaws.com:3306/airline_oopda";
		private static final String USER = "oopda";
		private static final String PASSWORD = "airline123";
		private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
		private static Connection mySQLconnection = createConnection();
		 
	    //private constructor
	    private static Connection createConnection() {
	        try {
	            // Load MySQL Java driver
	            Class.forName(DRIVER_CLASS);
	            // Establish Java MySQL connection
	            mySQLconnection = DriverManager.getConnection(URL, USER, PASSWORD);
	            return mySQLconnection;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    private static Connection getConnection() {
	    	return mySQLconnection;
	    }
	    
	    
	}
}
