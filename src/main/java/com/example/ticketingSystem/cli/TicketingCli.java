package com.example.ticketingSystem.cli;

import com.example.ticketingSystem.service.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TicketingCli implements CommandLineRunner {

    @Autowired
    private TicketingService ticketingService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting Ticketing System...");
        this.ticketingService.start();
        System.out.println("Ticketing System finished execution.");
    }
}
