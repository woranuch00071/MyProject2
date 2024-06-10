package domain;

public class Ticket {
    private int ticketId;
    private int trainNumber;
    private String travelDate;


    public Ticket(int ticketId, int trainNumber, String travelDate) {
        this.ticketId = ticketId;
        this.trainNumber = trainNumber;
        this.travelDate = travelDate;

    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public Ticket() {
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }





}
