package repository.jdbc;


import repository.DataStore;
import java.sql.*;


public class JDBCDataStore implements DataStore {
    private Connection connection;
    public JDBCDataStore() {
        connection = DatabaseConnection.connect();
    }

    public String loadData() {
        StringBuilder result = new StringBuilder();
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tickets")) {
            while (rs.next()) {
                result.append(rs.getString("ticketId")).append(" ");
                result.append(rs.getString("trainNumber")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}

