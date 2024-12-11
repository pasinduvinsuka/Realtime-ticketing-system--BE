package com.example.ticketingSystem.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Queue;

@Controller
public class TicketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public TicketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ticket")
    public void getTicketDetails(final Queue<String> tickets) {
        int currentTicketCount = tickets.size();
        simpMessagingTemplate.convertAndSend("/topic/tickets", currentTicketCount);
    }

}