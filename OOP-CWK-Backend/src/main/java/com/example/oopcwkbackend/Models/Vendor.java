package com.example.oopcwkbackend.Models;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;
    private static Logger logger = Logger.getLogger(Vendor.class.getName());
    private boolean finished = false;

    public Vendor(TicketPool ticketPool,int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        try {
            FileHandler fileHandler = new FileHandler("logs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.out.println("Can not find or open log file");
        }
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
                logger.info("Vendor thread interrupted");
            }
        }
        finished = true;
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }

    public boolean isFinished() {
        return finished;
    }
}
