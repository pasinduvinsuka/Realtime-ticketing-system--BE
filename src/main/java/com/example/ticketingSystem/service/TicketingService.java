package com.example.ticketingSystem.service;

import com.example.ticketingSystem.controller.TicketController;
import com.example.ticketingSystem.model.CustomerPriority;
import com.example.ticketingSystem.model.TicketPool;
import com.example.ticketingSystem.model.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class TicketingService {
    private static final Logger logger = LoggerFactory.getLogger(TicketingService.class);

    private final ConfigurationService configurationService;
    private final CustomerService customerService;
    private final VendorService vendorService;
    private final TicketController ticketController;
    private PriorityBlockingQueue<CustomerPriority> customerQueue;
    private Queue<Vendor> vendorQueue;
    TicketPool ticketPool;
    ;

    int maxCapacity;
    int totalTickets;
    int numVipCustomers;
    int numRegularCustomers;
    int numberOfVendors;
    int ticketReleaseRate;
    int customerRetrievalRate;

    @Autowired
    TicketingService(ConfigurationService configurationService, CustomerService customerService, VendorService vendorService, TicketController ticketController) {
        this.configurationService = configurationService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.ticketController = ticketController;
    }

    ExecutorService ticketExecutorService;

    //update the configurations
    private void updateConfigs() {
        maxCapacity = configurationService.getMaxCapacity();
        totalTickets = configurationService.getTotalTickets();
        numVipCustomers = configurationService.getNumVipCustomers();
        numRegularCustomers = configurationService.getNumRegularCustomers();
        numberOfVendors = configurationService.getNumberOfVendors();
        ticketReleaseRate = configurationService.getTicketReleaseRate();
        customerRetrievalRate = configurationService.getCustomerRetrievalRate();
        customerQueue = customerService.getCustomerQueue();
        vendorQueue = vendorService.getVendorQueue();
        ticketPool = new TicketPool(maxCapacity, totalTickets, ticketController);

        //Add vendors
        vendorService.addVendors(numberOfVendors, ticketPool, ticketReleaseRate);

        // Add customers
        customerService.addRegularCustomers(numRegularCustomers, ticketPool, customerRetrievalRate);
        customerService.addVipCustomers(numVipCustomers, ticketPool, customerRetrievalRate);
    }

    public void startExecutor() {
        while (!vendorQueue.isEmpty()) {
            ticketExecutorService.execute(vendorQueue.poll());
        }
        while (!customerQueue.isEmpty()) {
            ticketExecutorService.execute(customerQueue.poll().getCustomer());
        }
    }

    public void start() {
        ticketExecutorService = Executors.newCachedThreadPool();
        updateConfigs();
        startExecutor();
        ticketExecutorService.shutdown();
    }

    public void stopAll() {
        for (CustomerPriority customerPriority : customerQueue) {
            customerPriority.getCustomer().stop();
        }
        for (Vendor vendor : vendorQueue) {
            vendor.stop();
        }
    }

    public void restart() {
        stopAll();
        ticketExecutorService.shutdown();
        try {
            // Wait for existing tasks to finish
            if (!ticketExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                // Force shutdown if tasks don't finish in time
                ticketExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            // Force shutdown in case of interruption
            ticketExecutorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // Reinitialize the executor service
        ticketExecutorService = Executors.newCachedThreadPool();
        // Refresh configurations and queues
        updateConfigs();

        logger.info("Restarting with maxCapacity:{} ", maxCapacity);

        // Start processing with updated data
        startExecutor();
    }


}

