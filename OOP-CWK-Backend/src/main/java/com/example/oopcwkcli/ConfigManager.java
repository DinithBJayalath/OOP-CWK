package com.example.oopcwkcli;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ConfigManager {
    /**
     *The ConfigManager class is responsible for getting the configuration information
     * and creating the configuration object. It also validates the inputs
     * and saves the configuration to a JSON file.
     */
    private static Logger logger = Logger.getLogger(ConfigManager.class.getName());
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
    }

    public static void Configure() {
        Configuration configuration;
        Scanner scanner = new Scanner(System.in);
        int totalTickets =0, ticketReleaseRate =0, customerRetrievalRate =0, maxTicketCapacity = 0;
        while(totalTickets<=0) {
            System.out.print("Enter the total number of tickets: ");
            try {
                totalTickets = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! An integer is required.");
                logger.info("Invalid input entered!");
                scanner.nextLine();
                continue;
            }
            if (totalTickets <= 0) {
                System.out.println("Total number of tickets must be greater than 0!");
            }
        }
        while(ticketReleaseRate<=0 | ticketReleaseRate>totalTickets) {
            System.out.print("Enter the ticket release rate: ");
            try {
                ticketReleaseRate = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! An integer is required.");
                logger.info("Invalid input entered!");
                scanner.nextLine();
                continue;
            }
            if (ticketReleaseRate <= 0) {
                System.out.println("Ticket release rate must be greater than 0!");
            } else if (ticketReleaseRate > totalTickets) {
                System.out.println("Ticket release rate must be less than the total tickets!");
            }
        }
        while(customerRetrievalRate<=0 | customerRetrievalRate>totalTickets) {
            System.out.print("Enter the customer retrieval rate: ");
            try {
                customerRetrievalRate = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! An integer is required.");
                logger.info("Invalid input entered!");
                scanner.nextLine();
                continue;
            }
            if (customerRetrievalRate <= 0) {
                System.out.println("Customer retrieval rate must be greater than 0!");
            } else if (customerRetrievalRate > totalTickets) {
                System.out.println("Customer retrieval rate must be less than the total tickets!");
            }
        }
        while(maxTicketCapacity<=0 | maxTicketCapacity<totalTickets) {
            System.out.print("Enter the maximum ticket capacity: ");
            try {
                maxTicketCapacity = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! An integer is required.");
                logger.info("Invalid input entered!");
                scanner.nextLine();
                continue;
            }
            if (maxTicketCapacity <= 0) {
                System.out.println("Maximum ticket capacity must be greater than 0!");
            } else if (maxTicketCapacity<totalTickets){
                System.out.println("Maximum ticket capacity must be greater than or equal to the total number of tickets!");
            }
        }
        logger.info("All configuration parameters have been taken successfully.");
        configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        configuration.saveConfiguration();
        System.out.println("Configuration set successfully!");
    }
}