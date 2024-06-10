package repository;

import domain.Train;

public interface TrainRepository {
    String getAllTrains();
    void addTrain(Train train);

    Train getTrain(int trainNumber);
}
