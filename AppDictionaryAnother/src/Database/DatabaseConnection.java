package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/ACER/Downloads/Eng-Viet_Dictionary.db");
            return connection;
        }
        catch(Exception Err) {
            Err.printStackTrace();
            return null;
        }
    }
}
