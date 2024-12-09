package com.example.oopcwkcli;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;

    public Vendor(TicketPool ticketPool,int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) { // See if this code adds all the tickets up to the totalTickets
            try {
                Ticket ticket = new Ticket(i);
                ticketPool.addTicket(ticket);
                Thread.sleep(1000/ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor thread interrupted");
            }
        }
    }
}
