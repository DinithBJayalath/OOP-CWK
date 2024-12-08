package com.example.oopcwkcli;

import java.io.*;

import com.google.gson.Gson;
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
    private static Gson gson = new Gson();
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
        saveConfiguration(configuration);
        System.out.println("Configuration set successfully!");
        //TODO: These 2 lines are only for testing purposes, remove it before finishing the project.
        Configuration loadedConfiguration = loadConfiguration();
        System.out.println(loadedConfiguration.toString());
    }

    public static void saveConfiguration(Configuration configuration) {
        /**
         * This method saves the configuration to a JSON file.
         * @param configuration The configuration object to save.
         */
        try {
            File file = new File("src/main/resources/config.json");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(gson.toJson(configuration));
            logger.info("Configuration saved successfully.");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save configuration!");
            logger.info("Failed to save configuration.");
        }
    }

    public static Configuration loadConfiguration() {
        /**
         * This method loads the configuration from a JSON file.
         * @return The configuration object loaded from the file.
         */
        try {
            Scanner scanner = new Scanner(new FileReader("src/main/resources/config.json"));
            String json = scanner.nextLine();
            scanner.close();
            return gson.fromJson(json, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load configuration!");
            logger.info("Failed to load the configuration file.");
            return null;
        }
    }
}
