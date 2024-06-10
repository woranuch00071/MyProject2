package repository.jdbc;

import domain.Ticket;
import domain.Train;
import repository.TicketRepository;

import java.sql.*;

public class JdbcTicketRepository implements TicketRepository {
    private Connection connection;

    public JdbcTicketRepository() {
        connection = DatabaseConnection.connect();
    }


    @Override
    public boolean addTicket(Ticket ticket) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO tickets (trainNumber) VALUES (?)")) {
            stmt.setInt(1, ticket.getTrainNumber());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Ticket getTicket(int ticketId) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tickets WHERE ticketId = ?")) {
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Retrieve train details
                Train train = new Train(rs.getInt("trainNumber"), "", ""); // Retrieve train details appropriately
                return new Ticket(rs.getInt("ticketId"), train.getTrainNumber(), rs.getString("travelDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAllTickets() {
        StringBuilder result = new StringBuilder();
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tickets")
        ) {
            while (rs.next()) {
                result.append("TicketId: ");
                result.append(rs.getString("ticketId")).append("\n");
                result.append("Train number: ");
                result.append(rs.getString("trainNumber")).append("\n");
                result.append("Ticket issue date-time: ");
                result.append(rs.getString("travelDate")).append("\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }



    @Override
    public void deleteTickets() {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM tickets")) {

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
