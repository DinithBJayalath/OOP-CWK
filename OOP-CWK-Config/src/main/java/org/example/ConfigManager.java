package org.example;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.util.Scanner;

public class ConfigManager {
    /**
     *The ConfigManager class is responsible for getting the configuration information
     * and creating the configuration object. It also validates the inputs
     * and saves the configuration to a JSON file.
     */

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the total number of tickets: ");
        int totalTickets = scanner.nextInt();
        System.out.println("Enter the ticket release rate: ");
        int ticketReleaseRate = scanner.nextInt();
        System.out.println("Enter the customer retrieval rate: ");
        int customerRetrievalRate = scanner.nextInt();
        System.out.println("Enter the maximum ticket capacity: ");
        int maxTicketCapacity = scanner.nextInt();
        Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        //Try to improve validation by checking at every step if possible
        if (!isValid(configuration)) {
            System.out.println("Invalid configuration! Please make sure all values are positive.");
            return;
        }
        saveConfiguration(configuration);
        System.out.println("Configuration set successfully!");
        //To test the loading of the configuration and the rest of the code
        Configuration loadedConfiguration = loadConfiguration();
        System.out.println(loadedConfiguration.toString());
    }

    public static boolean isValid(Configuration configuration) {
        /**
         * This method checks if the configuration values are valid.
         * @param configuration The configuration object to validate.
         * @return True if the configuration is valid, false otherwise.
         */
        return configuration.getTotalTickets() > 0 && configuration.getTicketReleaseRate() > 0 && configuration.getCustomerRetrievalRate() > 0 && configuration.getMaxTicketCapacity() > 0;
    }

    public static void saveConfiguration(Configuration configuration) {
        /**
         * This method saves the configuration to a JSON file.
         * @param configuration The configuration object to save.
         */
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.json"));
            writer.write(gson.toJson(configuration));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration loadConfiguration() {
        /**
         * This method loads the configuration from a JSON file.
         * @return The configuration object loaded from the file.
         */
        try {
            Scanner scanner = new Scanner(new FileReader("config.json"));
            String json = scanner.nextLine();
            scanner.close();
            return gson.fromJson(json, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}