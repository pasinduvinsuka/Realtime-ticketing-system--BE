package com.example.ticketingSystem.service;

import com.example.ticketingSystem.dto.ConfigurationDTO;
import com.example.ticketingSystem.model.TicketPool;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Getter
@Service
public class ConfigurationService {

    private int maxCapacity;
    private int numVipCustomers;
    private int numRegularCustomers;
    private int numberOfVendors;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private CustomerService customerService;
    private TicketPool ticketPool;


    public ConfigurationService() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the max ticket capacity : ");
        this.maxCapacity = sc.nextInt();

        System.out.println("Enter the number of vendors : ");
        this.numberOfVendors = sc.nextInt();

        System.out.println("Enter the number of VIP customers : ");
        this.numVipCustomers = sc.nextInt();

        System.out.println("Enter the number of regular customers : ");
        this.numRegularCustomers = sc.nextInt();

        System.out.println("Enter the ticket release rate : ");
        this.ticketReleaseRate = sc.nextInt();

        System.out.println("Enter the customer retrieval rate : ");
        this.customerRetrievalRate = sc.nextInt();

    }

    public void updateConfigurations(ConfigurationDTO config) {
        this.maxCapacity = config.getMaxCapacity();
        this.numVipCustomers = config.getNumVipCustomers();
        this.numRegularCustomers = config.getRegularCustomers();
        this.numberOfVendors = config.getNumberOfVendors();
        this.ticketReleaseRate = config.getTicketReleaseRate();
        this.customerRetrievalRate = config.getCustomerRetrievalRate();

        customerService.addRegularCustomers(numRegularCustomers, ticketPool, customerRetrievalRate);
        customerService.addVipCustomers(numVipCustomers, ticketPool, customerRetrievalRate);
    }


}

