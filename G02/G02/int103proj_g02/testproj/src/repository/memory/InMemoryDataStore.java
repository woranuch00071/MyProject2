package repository.memory;

import domain.Ticket;
import domain.Train;
import domain.User;
import repository.DataStore;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataStore implements DataStore  {
    private List<Train> dataStore = new ArrayList<>();

    public InMemoryDataStore() {

        dataStore.add(new Train(1010, "Bangkok Mumbai","13:05"));
    }


    public void saveData(Train data) {
        dataStore.add(data);
    }


    public List<Train> loadTrainData() {
        return dataStore;
    }


    @Override
    public String loadData() {
        return null;
    }
}

