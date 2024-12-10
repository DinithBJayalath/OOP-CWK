package com.example.oopcwkcli;

import java.time.chrono.ThaiBuddhistEra;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Customer implements Runnable{
    private TicketPool ticketPool;
    private int ticketPurchaseRate;
    private static Logger logger = Logger.getLogger(Customer.class.getName());
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
    }

    public Customer(TicketPool ticketPool, int ticketPurchaseRate) {
        this.ticketPool = ticketPool;
        this.ticketPurchaseRate = ticketPurchaseRate;
    }

    @Override
    public void run() {
         while (!Thread.currentThread().isInterrupted()) {
            try {
                Ticket ticket = ticketPool.retrieveTicket();
                Thread.sleep(1000/ticketPurchaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted");
                logger.info("Customer thread interrupted");
            }
        }
    }
}
