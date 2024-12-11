package com.example.oopcwkcli;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Configuration implements Serializable {
    /**
     * The configuration class is a blueprint for the configuration object.
     */
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private static Gson gson = new Gson();
    //The following lines are used to create a logger object
    private static Logger logger = Logger.getLogger(ConfigManager.class.getName());
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
    }

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void saveConfiguration() {
        /**
         * This method saves the configuration to a JSON file.
         * @param configuration The configuration object to save.
         */
        try {
            File file = new File("src/main/resources/config.json");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(gson.toJson(this));
            logger.info("Configuration saved successfully.");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save configuration!");
            logger.warning("Failed to save configuration.");
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
            logger.warning("Failed to load the configuration file.");
            return null;
        }
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

    @Override
    public String toString() {
        return "Total tickets: " + totalTickets +
                "\nTicket release rate: " + ticketReleaseRate +
                "\nCustomer retrieval rate: " + customerRetrievalRate +
                "\nMaximum ticket capacity: " + maxTicketCapacity;
    }

}
