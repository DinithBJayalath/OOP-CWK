package org.example;

public class Main {
    public static void main(String[] args) {
        ConfigManager.Configure(); // Configure the application save the configuration to a json file
        Configuration configuration = ConfigManager.loadConfiguration(); // Load the configuration from the json file
        if (configuration == null) {
            System.out.println("Configuration not found!");
            return;
        }
        TicketPool ticketPool = new TicketPool(configuration.getMaxTicketCapacity());
    }
}
