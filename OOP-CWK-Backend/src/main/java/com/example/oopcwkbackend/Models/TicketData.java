package com.example.oopcwkbackend.Models;

public class TicketData {
    /**
     * Ticket data class,
     * responsible for storing ticket data that is sent to the ticket details component of the frontend
     */
    private int availableTickets;
    private int ticketsAdded;
    private int ticketsSold;

    public TicketData(int availableTickets, int ticketsAdded, int ticketsSold) {
        this.availableTickets = availableTickets;
        this.ticketsAdded = ticketsAdded;
        this.ticketsSold = ticketsSold;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getTicketsAdded() {
        return ticketsAdded;
    }

    public void setTicketsAdded(int ticketsAdded) {
        this.ticketsAdded = ticketsAdded;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }
}
