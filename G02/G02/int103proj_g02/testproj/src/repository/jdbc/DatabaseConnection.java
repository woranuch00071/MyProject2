package repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
    public static Connection connect(){
        String URL = "jdbc:mysql://localhost:3306/int103";
        String username = "root";
        String password = "1381312255v";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);


        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger("connection failed");
            System.out.println(ex.toString());
        }

        return connection;
    }
}
