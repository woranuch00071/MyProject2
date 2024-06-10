package service;

import domain.Ticket;
import domain.Train;
import domain.User;
import repository.TicketRepository;
import repository.TrainRepository;
import repository.UserRepository;
import repository.jdbc.JdbcTicketRepository;
import repository.jdbc.JdbcTrainRepository;
import repository.jdbc.JdbcUserRepository;

public class ReservationService {
    private UserRepository userRepository;
    private TrainRepository trainRepository;
    private TicketRepository ticketRepository;

    public ReservationService(JdbcUserRepository userRepository, JdbcTrainRepository trainRepository, JdbcTicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.trainRepository = trainRepository;
        this.ticketRepository = ticketRepository;
    }

    public void registerUser(User user) {
        userRepository.addUser(user);
    }

    public void addTrain(Train train) {
        trainRepository.addTrain(train);
    }

    public String getAllTrains() {
        return trainRepository.getAllTrains();
    }

    public String getAllTickets() {
        return ticketRepository.getAllTickets();
    }
    public boolean bookTicket(Ticket ticket) {
        return ticketRepository.addTicket(ticket);
        // Here you could add logic to link user with ticket

    }

    public void deleteTickets(){
        ticketRepository.deleteTickets();
    }
}
