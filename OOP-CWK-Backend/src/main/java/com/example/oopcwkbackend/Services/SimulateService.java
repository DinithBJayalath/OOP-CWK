package com.example.oopcwkbackend.Services;

import com.example.oopcwkbackend.Models.Customer;
import com.example.oopcwkbackend.Models.LoggingConfigurator;
import com.example.oopcwkbackend.Models.TicketPool;
import com.example.oopcwkbackend.Models.Vendor;
import com.example.oopcwkcli.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

@Service
public class SimulateService {

    private final TicketDetailsService ticketDetailsService;
    private final TicketPool ticketPool;
    private boolean is_running = false; // TODO: Implement a way to check if the application is running
    private static Logger logger = Logger.getLogger(SimulateService.class.getName());
    private Vendor[] vendors = new Vendor[10];
    private Customer[] customers = new Customer[10];
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    static {
        logger.addHandler(LoggingConfigurator.getFileHandler());
    }

    @Autowired
    public SimulateService(TicketDetailsService ticketDetailsService, TicketPool ticketPool) {
        this.ticketDetailsService = ticketDetailsService;
        this.ticketPool = ticketPool;
    }

    public void simulate(Configuration configuration) {
        executorService.submit(() -> {
            logger.info("Ticket simulation started");
            ticketPool.setMaxTicketCapacity(configuration.getMaxTicketCapacity());
            for (int i = 0; i < vendors.length; i++) {
                vendors[i] = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
                Thread vendorThread = new Thread(vendors[i], "Vendor " + i);
                vendorThread.start();
            }
            logger.info("All vendor threads started");
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate());
                Thread customerThread = new Thread(customers[i], "Customer " + i);
                customerThread.start();
            }
            logger.info("All customer threads started");
            is_running = true;
            while (is_running) {
                for (int i = 0; i < vendors.length; i++) {
                    if (vendors[i].isFinished()) {
                        vendors[i].stop();
                        vendors[i] = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
                        Thread vendorThread = new Thread(vendors[i], "Vendor " + i);
                        vendorThread.start();
                        logger.info("Vendor " + i + " thread restarted");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Simulation thread interrupted");
                        logger.info("Simulation thread interrupted");
                    }
                }
            }
        });
    }

    public void stopSimulation() {
        is_running = false;
        for (Vendor vendor : vendors) {
            vendor.stop();
        }
        for (Customer customer : customers) {
            customer.stop();
        }
    }
}
