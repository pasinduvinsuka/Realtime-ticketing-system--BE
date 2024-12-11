package com.example.ticketingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

//Producer class
@Data
@AllArgsConstructor
public class Vendor implements Runnable {
    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    @Override
    public void run() {
        if (ticketReleaseRate == 0) {
            System.out.println("New tickets are not adding to the pool since the release rate is 0");
        } else {
            while (true) {
                String ticket = "Ticket-" + vendorId + "-" + System.currentTimeMillis();
                ticketPool.addTicket(ticket, ticketReleaseRate);
            }
        }

    }
}
