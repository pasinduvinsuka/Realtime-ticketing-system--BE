package com.example.ticketingSystem.service;

import com.example.ticketingSystem.model.CustomerPriority;
import com.example.ticketingSystem.model.TicketPool;
import com.example.ticketingSystem.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@Service
public class TicketingService {

    private final ConfigurationService configurationService;
    private final CustomerService customerService;
    private final VendorService vendorService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    TicketingService(ConfigurationService configurationService, CustomerService customerService, VendorService vendorService, SimpMessagingTemplate simpMessagingTemplate) {
        this.configurationService = configurationService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void start() {
        int maxCapacity = configurationService.getMaxCapacity();
        int numVipCustomers = configurationService.getNumVipCustomers();
        int numRegularCustomers = configurationService.getNumRegularCustomers();
        int numberOfVendors = configurationService.getNumberOfVendors();
        int ticketReleaseRate = configurationService.getTicketReleaseRate();
        int customerRetrievalRate = configurationService.getCustomerRetrievalRate();


        PriorityBlockingQueue<CustomerPriority> customerQueue = customerService.getCustomerQueue();
        Queue<Vendor> vendorQueue = vendorService.getVendorQueue();
        try (ExecutorService ticketExecutorService = Executors.newCachedThreadPool()) {
            TicketPool ticketPool = new TicketPool(maxCapacity, 2, simpMessagingTemplate);

            //Add vendors
            vendorService.addVendors(numberOfVendors, ticketPool, ticketReleaseRate);

            // Add customers
            customerService.addRegularCustomers(numRegularCustomers, ticketPool, customerRetrievalRate);
            customerService.addVipCustomers(numVipCustomers, ticketPool, customerRetrievalRate);

            while (!vendorQueue.isEmpty()) {
                ticketExecutorService.execute(vendorQueue.poll());
            }

            while (!customerQueue.isEmpty()) {
                ticketExecutorService.execute(customerQueue.poll().getCustomer());
            }

            ticketExecutorService.shutdown();

        }

    }


}

