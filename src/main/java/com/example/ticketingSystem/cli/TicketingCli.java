package com.example.ticketingSystem.cli;

import com.example.ticketingSystem.service.ConfigurationService;
import com.example.ticketingSystem.service.TicketingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TicketingCli implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TicketingCli.class);


    @Autowired
    private TicketingService ticketingService;

    @Autowired
    private ConfigurationService configurationService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting Ticketing System...");
        this.configurationService.getConfigurations();
        this.ticketingService.start();
        logger.info("Ticketing System finished execution.");
    }
}
