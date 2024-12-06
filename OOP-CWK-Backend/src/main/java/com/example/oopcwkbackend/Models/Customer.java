package com.example.oopcwkbackend.Models;


public class Customer implements Runnable{
    private TicketPool ticketPool;
    private int ticketPurchaseRate;
    private int quantity;

    public Customer(TicketPool ticketPool, int ticketPurchaseRate, int quantity) {
        this.ticketPool = ticketPool;
        this.ticketPurchaseRate = ticketPurchaseRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            try {
                Ticket ticket = ticketPool.retrieveTicket();
                Thread.sleep(1000/ticketPurchaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted");
            }
        }
    }
}
