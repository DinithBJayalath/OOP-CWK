package com.example.oopcwkbackend.Services;

import com.example.oopcwkbackend.Models.Customer;
import com.example.oopcwkbackend.Models.TicketPool;
import com.example.oopcwkbackend.Models.Vendor;
import com.example.oopcwkcli.Configuration;
import org.springframework.stereotype.Service;

@Service
public class SimulateService {

    public void simulate(Configuration configuration) {
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
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), (int) (Math.random() * 20));
            Thread customerThread = new Thread(customers[i], "Customer " + i);
            customerThread.start();
        }
    }
}
