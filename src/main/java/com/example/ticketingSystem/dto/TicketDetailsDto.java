package com.example.ticketingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TicketDetailsDto {
    private final int totalTickets;
}
