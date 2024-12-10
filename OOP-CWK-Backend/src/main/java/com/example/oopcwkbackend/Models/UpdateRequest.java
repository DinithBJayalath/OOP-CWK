package com.example.oopcwkbackend.Models;

public class UpdateRequest {
    private int vendors;
    private int customers;

    public UpdateRequest(int vendors, int customers) {
        this.vendors = vendors;
        this.customers = customers;
    }

    public int getVendors() {
        return vendors;
    }

    public void setVendors(int vendors) {
        this.vendors = vendors;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }
}
