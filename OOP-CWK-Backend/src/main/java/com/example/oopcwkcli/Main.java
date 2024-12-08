package com.example.oopcwkcli;

public class Main {
    public static void main(String[] args) {
        ConfigManager.Configure(); // Configure the application save the configuration to a json file
        Configuration configuration = ConfigManager.loadConfiguration(); // Load the configuration from the json file
        if (configuration == null) {
            System.out.println("Configuration not found!");
            return;
        }
        boolean is_running = false; // TODO: Implement a way to check if the application is running
        TicketPool ticketPool = new TicketPool(configuration.getMaxTicketCapacity());
        Vendor[] vendors = new Vendor[10];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
            Thread vendorThread = new Thread(vendors[i], "Vendor " + i);
            vendorThread.start();
        }
        Customer[] customers = new Customer[10];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), (int)(Math.random()*10));
            Thread customerThread = new Thread(customers[i], "Customer " + i);
            customerThread.start();
        }
    }
}
