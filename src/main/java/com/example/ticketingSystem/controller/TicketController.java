package com.example.ticketingSystem.controller;

import com.example.ticketingSystem.dto.TicketDetailsDto;
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
    public void getTicketDetails(Queue<String> tickets, int vipCount, int nonVipCount) {
        int currentTicketCount = tickets.size();
        TicketDetailsDto ticketDetailsDto = new TicketDetailsDto(currentTicketCount, vipCount, nonVipCount);
        simpMessagingTemplate.convertAndSend("/topic/tickets", ticketDetailsDto);
    }

}