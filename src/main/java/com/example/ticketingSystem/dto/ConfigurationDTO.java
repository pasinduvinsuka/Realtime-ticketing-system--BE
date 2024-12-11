package com.example.ticketingSystem.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationDTO {
    private int maxCapacity;
    private int numVipCustomers;
    private int regularCustomers;
    private int numberOfVendors;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
}
