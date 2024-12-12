package com.example.ticketingSystem.dto;

public class TicketDetailsDto {

    private final int totalTickets;
    private final int vipCount;
    private final int nonVipCount;

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getVipCount() {
        return vipCount;
    }

    public int getNonVipCount() {
        return nonVipCount;
    }

    public TicketDetailsDto(int totalTickets, int vipCount, int nonVipCount) {
        this.totalTickets = totalTickets;
        this.vipCount = vipCount;
        this.nonVipCount = nonVipCount;
    }

}
