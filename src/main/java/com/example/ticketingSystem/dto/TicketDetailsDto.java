package com.example.ticketingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


public class TicketDetailsDto {
    public int getTotalTickets() {
        return totalTickets;
    }

    public TicketDetailsDto(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    private final int totalTickets;
}
