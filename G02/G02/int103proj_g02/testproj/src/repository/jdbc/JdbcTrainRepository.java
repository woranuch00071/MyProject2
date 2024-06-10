package repository.jdbc;

import domain.Train;
import repository.TrainRepository;

import java.sql.*;

public class JdbcTrainRepository implements TrainRepository {
    private Connection connection;

    public JdbcTrainRepository() {
        connection = DatabaseConnection.connect();
    }
    @Override
    public String getAllTrains() {
        StringBuilder result = new StringBuilder();
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM trains")) {
            while (rs.next()) {
                result.append(rs.getString("trainNumber")).append(" ");
                result.append(rs.getString("route")).append(" ");
                result.append(rs.getString("departureTime")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    public void addTrain(Train train) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO trains (trainNumber, route, departureTime) VALUES (?, ?, ?)")) {
            stmt.setInt(1, train.getTrainNumber());
            stmt.setString(2, train.getRoute());
            stmt.setString(3, train.getDepartureTime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Train getTrain(int trainNumber) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM trains WHERE trainNumber = ?")) {
            stmt.setInt(1, trainNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Train(rs.getInt("trainNumber"), rs.getString("route"), rs.getString("departureTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
