package com.example.ticketingSystem.model;

import java.sql.Date;
import java.sql.Time;

//Producer class
public class Vendor implements Runnable {
    private final int vendorId;
    TicketPool ticketPool;
    int ticketReleaseRate = 100; // how often the tickets will be released (in here each second )

    public Vendor(int vendorId, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        try {
            while (true) {

                String ticket = "Ticket-" + vendorId + "-" + System.currentTimeMillis();
                ticketPool.addTicket(ticket);
//                System.out.println("Ticket added: " + ticket + "vendor - " + vendorId + " ## " + Thread.currentThread().getName() + " ##" + Thread.currentThread().getState());
                Thread.sleep(ticketReleaseRate);//ticket release rate
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
