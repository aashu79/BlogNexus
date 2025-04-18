package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helps connect to the application's database.
 */
public class DbConfig {

    // The name of the database we want to use.
    private static final String DB_NAME = "blognexus";

    // The full address to connect to the MySQL database (using localhost).
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + 
    	    "?useSSL=false&allowPublicKeyRetrieval=true";
    // The username for logging into the database (using "root").
    private static final String USERNAME = "root";

    // The password for logging into the database (currently empty - okay for local tests, but not secure!).
    private static final String PASSWORD = "";


    /**
     * Creates and returns a connection to the database.
     *
     * This method loads the necessary database driver and then uses the URL,
     * username, and password defined above to connect.
     *
     * Make sure to close the connection when you're done with it!
     *
     * @return A Connection object to talk to the database.
     * @throws SQLException If there's a problem connecting to the database (like wrong password or DB is down).
     * @throws ClassNotFoundException If the MySQL driver JAR file is missing or not added to the project correctly.
     */
    public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
        // Make sure the Java program knows how to talk to MySQL.
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the database using the details above.
            System.out.println("Driver loaded successfully!");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found! Check your classpath.");
            e.printStackTrace();
            return null;
        }

      
    }

}