package javafxapplication9;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//THIS CLASS IS USED TO RETURN THE DRIVER CONNECTING THE MYSQL DATABASE

public class DBConnection {
private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN = "jdbc:mysql://localhost/hospital";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
    }


    

    
}
