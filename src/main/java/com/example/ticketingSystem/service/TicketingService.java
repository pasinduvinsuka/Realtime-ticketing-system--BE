package com.example.ticketingSystem.service;

import com.example.ticketingSystem.model.CustomerPriority;
import com.example.ticketingSystem.model.TicketPool;
import com.example.ticketingSystem.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@Service
public class TicketingService {

    private final ConfigurationService configurationService;
    private final CustomerService customerService;

    @Autowired
    TicketingService(ConfigurationService configurationService, CustomerService customerService) {
        this.configurationService = configurationService;
        this.customerService = customerService;
    }

    public void start() {
        int maxCapacity = configurationService.getMaxCapacity();
        int numVipCustomers = configurationService.getNumVipCustomers();
        int regularCustomers = configurationService.getRegularCustomers();
        int numberOfVendors = configurationService.getNumberOfVendors();
        int ticketReleaseRate = configurationService.getTicketReleaseRate();
        int customerRetrievalRate = configurationService.getCustomerRetrievalRate();
        PriorityBlockingQueue<CustomerPriority> customerQueue = customerService.getCustomerQueue();

        try (ExecutorService ticketExecutorService = Executors.newCachedThreadPool()) {
            TicketPool ticketPool = new TicketPool(maxCapacity, 2);

            //add 2 vendors to the thread pool
            for (int i = 0; i < numberOfVendors; i++) {
                ticketExecutorService.execute(new Vendor(i, ticketPool, ticketReleaseRate));
            }

            // Add customers
            customerService.addRegularCustomers(regularCustomers, ticketPool, customerRetrievalRate);
            customerService.addVipCustomers(numVipCustomers, ticketPool, customerRetrievalRate);

            while (!customerQueue.isEmpty()) {
                ticketExecutorService.execute(customerQueue.poll().getCustomer());
            }

            ticketExecutorService.shutdown();

        }

    }


}

