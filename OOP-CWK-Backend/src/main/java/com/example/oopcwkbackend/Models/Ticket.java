package com.example.oopcwkbackend.Models;

public class Ticket {
    /**
     * Ticket class, the data class for storing ticket data
     */
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
        return "Ticket{\n" +
                "ticketId=" + ticketId +
                ", \nisAvailable=" + isAvailable +
                "\n}";
    }
}
