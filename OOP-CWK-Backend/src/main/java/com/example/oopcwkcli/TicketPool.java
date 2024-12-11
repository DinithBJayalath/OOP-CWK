package com.example.oopcwkcli;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TicketPool {
    /**
     * The TicketPool class is responsible for managing the tickets in the tickets queue.
     */
    private Queue<Ticket> tickets = new ConcurrentLinkedQueue<>();
    private int maxTicketCapacity;
    private static Logger logger = Logger.getLogger(TicketPool.class.getName());
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
    }

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public synchronized void addTicket(Ticket ticket) {
        /**
         * This method adds a ticket to the ticket pool.
         * @param ticket The ticket to add to the pool.
         */
        while (tickets.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ticket addition interrupted");
                logger.warning("Ticket addition interrupted");
            }
        }
        tickets.add(ticket);
        System.out.println("Ticket " + ticket.getTicketId() + " added to the pool by Vendor " + Thread.currentThread().threadId());
        notifyAll();
    }

    public synchronized Ticket retrieveTicket() {
        /**
         * This method retrieves a ticket from the ticket pool.
         * @return The ticket retrieved from the pool.
         */
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ticket retrieval interrupted");
                logger.warning("Ticket retrieval interrupted");
            }
        }
        Ticket ticket = tickets.poll();
        ticket.sellTicket();
        System.out.println("Ticket " + ticket.getTicketId() + " has been bought by Customer " + Thread.currentThread().threadId());
        notifyAll();
        return ticket;
    }
}
