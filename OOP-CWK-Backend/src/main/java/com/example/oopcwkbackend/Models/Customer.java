package com.example.oopcwkbackend.Models;


import java.util.logging.Logger;

public class Customer implements Runnable{
    /**
     * Customer class, responsible for purchasing tickets from the tickets queue.
     */
    private TicketPool ticketPool;
    private int ticketPurchaseRate;
    private static Logger logger = Logger.getLogger(Customer.class.getName());
    static {
        logger.addHandler(LoggingConfigurator.getFileHandler());
    }

    public Customer(TicketPool ticketPool, int ticketPurchaseRate) {
        this.ticketPool = ticketPool;
        this.ticketPurchaseRate = ticketPurchaseRate;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Ticket ticket = ticketPool.retrieveTicket();
                Thread.sleep(1000*ticketPurchaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted");
                logger.warning("Customer thread interrupted");
                break;
            }
        }
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }
}
