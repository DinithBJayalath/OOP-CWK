package com.example.oopcwkbackend.Models;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.*;

@Component
public class TicketPool {
    private Queue<Ticket> tickets = new ConcurrentLinkedQueue<>();
    private int maxTicketCapacity;
    private int ticketsAdded = 0;
    private int ticketsSold = 0;
    private static Logger logger = Logger.getLogger(TicketPool.class.getName());

    static {
        logger.addHandler(LoggingConfigurator.getFileHandler());
    }

    public TicketPool() {

    }

    public synchronized void addTicket(Ticket ticket) {
        while (tickets.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ticket addition interrupted");
                logger.info("Ticket addition interrupted");
            }
        }
        tickets.add(ticket);
        ticketsAdded++;
        System.out.println("Ticket " + ticket.getTicketId() + " added to the pool by Vendor " + Thread.currentThread().threadId());
        notifyAll();
    }

    public synchronized Ticket retrieveTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ticket retrieval interrupted");
                logger.info("Ticket retrieval interrupted");
            }
        }
        Ticket ticket = tickets.poll();
        ticket.sellTicket();
        ticketsSold++;
        System.out.println("Ticket " + ticket.getTicketId() + " has been bought by Customer " + Thread.currentThread().threadId());
        notifyAll();
        return ticket;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTicketPoolSize() {
        return tickets.size();
    }

    public int getTicketsAdded() {
        return ticketsAdded;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void resetTicketPool() {
        tickets.clear();
        ticketsAdded = 0;
        ticketsSold = 0;
        logger.info("Ticket pool has been reset");
    }
}
