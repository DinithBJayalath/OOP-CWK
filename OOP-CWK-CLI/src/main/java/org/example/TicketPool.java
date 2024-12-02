package org.example;

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
            }
        }
        tickets.add(ticket);
        System.out.println("Ticket " + ticket.getTicketId() + " added to the pool.");
        notifyAll();
    }

    public synchronized Ticket retrieveTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket ticket = tickets.poll();
        ticket.sellTicket();
        System.out.println("Ticket " + ticket.getTicketId() + " retrieved from the pool.");
        notifyAll();
        return ticket;
    }
}
