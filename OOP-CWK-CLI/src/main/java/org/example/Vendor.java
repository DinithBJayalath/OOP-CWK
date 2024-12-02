package org.example;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
               Ticket ticket = new Ticket((int) (Math.random() * 1000));
                ticketPool.addTicket(ticket);
                Thread.sleep(1000/ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
