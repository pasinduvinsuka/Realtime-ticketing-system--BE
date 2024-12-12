package com.example.ticketingSystem.model;

public class CustomerPriority implements Comparable<CustomerPriority> {
    Customer customer;
    boolean isVIP;

    public CustomerPriority(Customer customer, boolean isVIP) {
        this.customer = customer;
        this.isVIP = isVIP;
    }

    @Override
    public int compareTo(CustomerPriority o) {
        return Boolean.compare(o.isVIP, isVIP);
    }

    public Customer getCustomer() {
        return customer;
    }
}
