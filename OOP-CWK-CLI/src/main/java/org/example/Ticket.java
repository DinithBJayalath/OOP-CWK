package org.example;

public class Ticket {
    private int ticketId;
    private boolean isAvailable = true;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void sellTicket() {
        isAvailable = false;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
