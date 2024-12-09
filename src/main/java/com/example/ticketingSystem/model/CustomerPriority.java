package com.example.ticketingSystem.model;

public class CustomerPriority implements Comparable<CustomerPriority> {
    Runnable customer;
    boolean isVIP;

    public CustomerPriority(Runnable customer, boolean isVIP) {
        this.customer = customer;
        this.isVIP = isVIP;
    }

    @Override
    public int compareTo(CustomerPriority o) {
        return Boolean.compare(o.isVIP, isVIP);
    }

    public Runnable getCustomer() {
        return customer;
    }
}
