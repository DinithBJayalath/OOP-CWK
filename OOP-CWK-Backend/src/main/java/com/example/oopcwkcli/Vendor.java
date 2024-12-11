package com.example.oopcwkcli;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Vendor implements Runnable {
    /**
     * The Vendor class is responsible for adding tickets to the tickets queue.
     */
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;
    private static Logger logger = Logger.getLogger(Vendor.class.getName());
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
    }

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
                Thread.sleep(1000*ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor thread interrupted");
                logger.warning("Vendor thread interrupted");
            }
        }
    }
}
