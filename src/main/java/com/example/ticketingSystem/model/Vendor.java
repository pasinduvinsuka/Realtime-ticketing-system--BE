package com.example.ticketingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Time;

//Producer class
public class Vendor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Vendor.class);

    public Vendor(int vendorId, TicketPool ticketPool, int ticketReleaseRate) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    @Override
    public void run() {
        if (ticketReleaseRate == 0) {
            logger.info("New tickets are not adding to the pool since the release rate is 0");
        } else {
            while (true) {
                String ticket = "Ticket-" + vendorId + "-" + System.currentTimeMillis();
                ticketPool.addTicket(ticket, ticketReleaseRate);
            }
        }

    }
}
