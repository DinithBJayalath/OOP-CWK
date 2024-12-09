package com.example.oopcwkbackend.Services;

import com.example.oopcwkbackend.Models.Customer;
import com.example.oopcwkbackend.Models.TicketPool;
import com.example.oopcwkbackend.Models.Vendor;
import com.example.oopcwkcli.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class SimulateService {

    private final TicketDetailsService ticketDetailsService;
    private final TicketPool ticketPool;
    private static Logger logger = Logger.getLogger(SimulateService.class.getName());

    @Autowired
    public SimulateService(TicketDetailsService ticketDetailsService, TicketPool ticketPool) {
        this.ticketDetailsService = ticketDetailsService;
        this.ticketPool = ticketPool;
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
    }

    public void simulate(Configuration configuration) {
        logger.info("Ticket simulation started");
        boolean is_running = false; // TODO: Implement a way to check if the application is running
        ticketPool.setMaxTicketCapacity(configuration.getMaxTicketCapacity());
        Vendor[] vendors = new Vendor[10];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
            Thread vendorThread = new Thread(vendors[i], "Vendor " + i);
            vendorThread.start();
        }
        logger.info("Vendor threads started");
        Customer[] customers = new Customer[10];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate());
            Thread customerThread = new Thread(customers[i], "Customer " + i);
            customerThread.start();
        }
        logger.info("Customer threads started");
    }
}
