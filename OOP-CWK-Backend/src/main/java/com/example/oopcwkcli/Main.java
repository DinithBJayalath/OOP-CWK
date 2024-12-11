package com.example.oopcwkcli;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
        Thread listenerThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 'q' to quit the application");
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Quitting the application...");
                    logger.info("Quitting the application...");
                    System.exit(0);
                }
            }
        });
        ConfigManager.Configure(); // Configure the application save the configuration to a json file
        listenerThread.start();
        try {
            Thread.sleep(2000); // Wait for the listener thread to start
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted");
            logger.info("Thread interrupted");
        }
        Configuration configuration = Configuration.loadConfiguration(); // Load the configuration from the json file
        if (configuration == null) {
            System.out.println("Configuration not found!");
            logger.info("Configuration not found!");
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
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate());
            Thread customerThread = new Thread(customers[i], "Customer " + i);
            customerThread.start();
        }
    }
}
