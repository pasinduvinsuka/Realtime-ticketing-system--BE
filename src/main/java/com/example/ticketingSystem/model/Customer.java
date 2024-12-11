package com.example.ticketingSystem.model;

import lombok.AllArgsConstructor;

//Consumer class
@AllArgsConstructor
public class Customer implements Runnable {
    private final int customerId;
    private final boolean isVIP;
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    @Override
    public void run() {
        if (customerRetrievalRate == 0) {
            System.out.println("Customers are not retrieving the tickets since retrieval rate is 0.");
        } else {

            try {
                while (true) {
                    for (int i = 0; i < customerRetrievalRate; i++) {
                        String ticket = ticketPool.removeTicket();
                        System.out.println("Ticket " + ticket + " purchased by customer :" + customerId + " vip status :" + isVIP + " ## " + Thread.currentThread().getName() + " ##" + Thread.currentThread().getState());
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}


