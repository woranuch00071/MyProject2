package domain;

public class Train {
    private int trainNumber;
    private String route;
    private String departureTime;

    public Train(int trainNumber, String route, String departureTime) {
        this.trainNumber = trainNumber;
        this.route = route;
        this.departureTime = departureTime;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
