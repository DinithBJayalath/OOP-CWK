package org.example;

import java.io.Serializable;

public class Configuration implements Serializable {
    /**
     * The configuration class is a blueprint for the configuration object.
     */
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public String toString() {
        return "Total tickets: " + totalTickets + "\nTicket release rate: " + ticketReleaseRate + "\nCustomer retrieval rate: " + customerRetrievalRate + "\nMaximum ticket capacity: " + maxTicketCapacity;
    }

}
