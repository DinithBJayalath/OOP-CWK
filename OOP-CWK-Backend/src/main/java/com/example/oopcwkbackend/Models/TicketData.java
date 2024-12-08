package com.example.oopcwkbackend.Models;

public class TicketData {
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

    public int getTicketsAdded() {
        return ticketsAdded;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }
}
