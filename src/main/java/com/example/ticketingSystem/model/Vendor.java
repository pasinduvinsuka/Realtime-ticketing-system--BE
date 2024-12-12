package com.example.ticketingSystem.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Producer class
public class Vendor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Vendor.class);
    private volatile boolean running = true;

    public Vendor(int vendorId, TicketPool ticketPool, int ticketReleaseRate) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        if (ticketReleaseRate == 0) {
            logger.info("New tickets are not adding to the pool since the release rate is 0");
        } else {
            while (running) {
                String ticket = "Ticket-" + vendorId + "-" + System.currentTimeMillis();
                ticketPool.addTicket(ticket, ticketReleaseRate);
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
