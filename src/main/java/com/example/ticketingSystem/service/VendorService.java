package com.example.ticketingSystem.service;

import com.example.ticketingSystem.model.TicketPool;
import com.example.ticketingSystem.model.Vendor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class VendorService {
    private final Queue<Vendor> vendorList = new LinkedList<>();

    public void addVendors(int numberOfVendors, TicketPool ticketPool, int ticketReleaseRate) {
        for (int i = 0; i < numberOfVendors; i++) {
            Vendor vendor = new Vendor(i, ticketPool, ticketReleaseRate);
            vendorList.add(vendor);
        }
    }

    public Queue<Vendor> getVendorQueue() {
        return vendorList;
    }
}
