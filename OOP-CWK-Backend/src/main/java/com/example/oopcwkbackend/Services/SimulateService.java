package com.example.oopcwkbackend.Services;

import com.example.oopcwkbackend.Models.Customer;
import com.example.oopcwkbackend.Models.LoggingConfigurator;
import com.example.oopcwkbackend.Models.TicketPool;
import com.example.oopcwkbackend.Models.Vendor;
import com.example.oopcwkcli.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

@Service
public class SimulateService {

    private final TicketDetailsService ticketDetailsService;
    private final TicketPool ticketPool;
    private boolean is_running = false;
    private static Logger logger = Logger.getLogger(SimulateService.class.getName());
    private ArrayList<Vendor> vendors = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
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
            configuration.saveConfiguration();
            logger.info("Ticket simulation started");
            ticketPool.setMaxTicketCapacity(configuration.getMaxTicketCapacity());
            for (int i = 0; i < 10; i++) {
                Vendor vendor = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
                vendors.add(vendor);
                Thread vendorThread = new Thread(vendor, "Vendor " + i);
                vendorThread.start();
            }
            logger.info("All vendor threads started");
            for (int i = 0; i < 10; i++) {
                Customer customer = new Customer(ticketPool, configuration.getCustomerRetrievalRate());
                customers.add(customer);
                Thread customerThread = new Thread(customer, "Customer " + i);
                customerThread.start();
            }
            logger.info("All customer threads started");
            is_running = true;
            while (is_running) {
                for (int i = 0; i < vendors.size(); i++) {
                    Vendor vendor = vendors.get(i);
                    if (vendor.isFinished()) {
                        vendor.stop();
                        Vendor newVendor = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
                        Thread vendorThread = new Thread(newVendor, "Vendor " + i);
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

    public void updateArrays(int vendorsCount, int customersCount) {
        Configuration configuration = Configuration.loadConfiguration();
        if (configuration == null) {
            return;
        }
        synchronized (this) {
            while (vendors.size() > vendorsCount) {
                Vendor vendor = vendors.get(vendors.size() - 1);
                vendor.stop();
                vendors.remove(vendor);
            }
            while (vendors.size() < vendorsCount) {
                Vendor vendor = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
                vendors.add(vendor);
                Thread vendorThread = new Thread(vendor, "Vendor " + vendors.size());
                vendorThread.start();
            }
            while (customers.size() > customersCount) {
                Customer customer = customers.get(customers.size() - 1);
                customer.stop();
                customers.remove(customer);
            }
            while (customers.size() < customersCount) {
                Customer customer = new Customer(ticketPool, configuration.getCustomerRetrievalRate());
                customers.add(customer);
                Thread customerThread = new Thread(customer, "Customer " + customers.size());
                customerThread.start();
            }
        }
    }

    public void stopSimulation() {
        is_running = false;
        for (Vendor vendor : vendors) {
            vendor.stop();
        }
        for (Customer customer : customers) {
            customer.stop();
        }
        logger.info("Ticket simulation stopped");
    }
}
