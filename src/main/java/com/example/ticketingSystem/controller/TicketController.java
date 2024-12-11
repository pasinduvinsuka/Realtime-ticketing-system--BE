package com.example.ticketingSystem.controller;

import com.example.ticketingSystem.dto.ResponseDto;
import com.example.ticketingSystem.dto.TicketDetailsDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TicketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public TicketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ticket")
    public void getTicketDetails(String data) {
        // Process the incoming data (you can replace this with actual business logic)
        System.out.println("Received data: " + data);

        // Send updated data to all subscribers of /topic/data
        simpMessagingTemplate.convertAndSend("/topic/data", "Updated value: " + data);
    }

}