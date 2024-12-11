package com.example.ticketingSystem.dto;


public class ConfigurationDTO {
    public ConfigurationDTO(int maxCapacity, int totalTickets, int numVipCustomers, int regularCustomers, int numberOfVendors, int ticketReleaseRate, int customerRetrievalRate) {
        this.maxCapacity = maxCapacity;
        this.totalTickets = totalTickets;
        this.numVipCustomers = numVipCustomers;
        this.regularCustomers = regularCustomers;
        this.numberOfVendors = numberOfVendors;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getNumVipCustomers() {
        return numVipCustomers;
    }

    public void setNumVipCustomers(int numVipCustomers) {
        this.numVipCustomers = numVipCustomers;
    }

    public int getRegularCustomers() {
        return regularCustomers;
    }

    public void setRegularCustomers(int regularCustomers) {
        this.regularCustomers = regularCustomers;
    }

    public int getNumberOfVendors() {
        return numberOfVendors;
    }

    public void setNumberOfVendors(int numberOfVendors) {
        this.numberOfVendors = numberOfVendors;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    private int maxCapacity;
    private int totalTickets;
    private int numVipCustomers;
    private int regularCustomers;
    private int numberOfVendors;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
}
