package org.example;

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
        while (!Thread.currentThread().isInterrupted()) { // See if this code adds all the tickets up to the totalTickets
            try {
               Ticket ticket = new Ticket((int) (Math.random() * 1000));
                ticketPool.addTicket(ticket);
                Thread.sleep(1000/ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); //why this and not throw an exception
            }
        }
    }
}
