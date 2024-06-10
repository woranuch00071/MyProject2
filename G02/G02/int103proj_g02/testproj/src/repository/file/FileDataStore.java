package repository.file;

import domain.Ticket;
import domain.Train;
import domain.User;
import repository.DataStore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileDataStore implements DataStore {
    private Path filePath = Paths.get("data.txt");


    public void saveData(String data) {
        try {
            Files.write(filePath, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String loadData() {
        try {
            return new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    public void saveUser(User user) {

    }


    public User loadUser(String username) {
        return null;
    }


    public void saveTrain(Train train) {

    }


    public Train loadTrain(int trainNumber) {
        return null;
    }


    public void saveTicket(Ticket ticket) {

    }


    public Ticket loadTicket(int ticketId) {
        return null;
    }


    public List<Ticket> loadAllTickets() {
        return null;
    }
}

