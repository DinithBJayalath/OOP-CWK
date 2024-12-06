package com.example.oopcwkbackend.Models;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketPool {
    private Queue<Ticket> tickets = new ConcurrentLinkedQueue<>();
    private int maxTicketCapacity;

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public synchronized void addTicket(Ticket ticket) {
        while (tickets.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ticket addition interrupted");
            }
        }
        tickets.add(ticket);
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
            }
        }
        Ticket ticket = tickets.poll();
        ticket.sellTicket();
        System.out.println("Ticket " + ticket.getTicketId() + " has been bought by Customer " + Thread.currentThread().threadId());
        notifyAll();
        return ticket;
    }
}
