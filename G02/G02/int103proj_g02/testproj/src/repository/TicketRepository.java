package repository;

import domain.Ticket;

public interface TicketRepository {
    boolean addTicket(Ticket ticket);
    Ticket getTicket(int ticketId);

    String getAllTickets();

    void deleteTickets();
}
