package com.example.ticketingSystem.service;

import com.example.ticketingSystem.model.TicketPool;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService implements Runnable {
    private final int customerId;
    private final TicketPool ticketPool;
    private final boolean isVIP;

    public ConfigurationService(int customerId, boolean isVIP, TicketPool ticketPool) {
        this.customerId = customerId;
        this.isVIP = isVIP;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        try {
            while (true) {
//                System.out.println("Customer " + customerId + " attempting to buy a ticket..." + "####isVip: " + isVIP);
                String ticket = ticketPool.removeTicket();
                System.out.println("Ticket " + ticket + " purchased by customer :" + customerId + " vip status :" + isVIP + " ## " + Thread.currentThread().getName() + " ##" + Thread.currentThread().getState());
                int customerRetrievalRate = 1;
                Thread.sleep(customerRetrievalRate);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

