package org.example;

import java.util.Scanner;

public class ConfigManager {
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
        System.out.println("Configuration set successfully!");
    }
}