package com.example.ticketingSystem.model;

//Consumer class
public class Customer implements Runnable {
    private final boolean isVIP;
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private volatile boolean running = true;

    public Customer(int customerId, boolean isVIP, TicketPool ticketPool, int customerRetrievalRate) {
        this.isVIP = isVIP;
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        if (customerRetrievalRate == 0) {
            System.out.println("Customers are not retrieving the tickets since retrieval rate is 0.");
        } else {
            while (running) {
                for (int i = 0; i < customerRetrievalRate; i++) {
                    String ticket = ticketPool.removeTicket(isVIP);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Preserve the interrupted status
                        break;
                    }
                }
            }
        }
    }
}



