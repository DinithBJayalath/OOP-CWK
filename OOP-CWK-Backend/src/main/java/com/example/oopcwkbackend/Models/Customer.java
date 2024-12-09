package com.example.oopcwkbackend.Models;


public class Customer implements Runnable{
    private TicketPool ticketPool;
    private int ticketPurchaseRate;

    public Customer(TicketPool ticketPool, int ticketPurchaseRate) {
        this.ticketPool = ticketPool;
        this.ticketPurchaseRate = ticketPurchaseRate;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Ticket ticket = ticketPool.retrieveTicket();
                Thread.sleep(1000/ticketPurchaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted");
            }
        }
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }
}
