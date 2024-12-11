package com.example.ticketingSystem.service;

import com.example.ticketingSystem.dto.ConfigurationDTO;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Scanner;


@Service
public class ConfigurationService {

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getNumVipCustomers() {
        return numVipCustomers;
    }

    public void setNumVipCustomers(int numVipCustomers) {
        this.numVipCustomers = numVipCustomers;
    }

    public int getNumRegularCustomers() {
        return numRegularCustomers;
    }

    public void setNumRegularCustomers(int numRegularCustomers) {
        this.numRegularCustomers = numRegularCustomers;
    }

    public int getNumberOfVendors() {
        return numberOfVendors;
    }

    public void setNumberOfVendors(int numberOfVendors) {
        this.numberOfVendors = numberOfVendors;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    private int maxCapacity;
    private int totalTickets;
    private int numVipCustomers;
    private int numRegularCustomers;
    private int numberOfVendors;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private TicketPool ticketPool;


    public void getConfigurations() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the max ticket capacity : ");
        this.maxCapacity = sc.nextInt();

        System.out.println("Enter the total capacity:");
        this.totalTickets = sc.nextInt();

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
        this.totalTickets = config.getTotalTickets();
        this.numVipCustomers = config.getNumVipCustomers();
        this.numRegularCustomers = config.getRegularCustomers();
        this.numberOfVendors = config.getNumberOfVendors();
        this.ticketReleaseRate = config.getTicketReleaseRate();
        this.customerRetrievalRate = config.getCustomerRetrievalRate();
//
//        customerService.addRegularCustomers(numRegularCustomers, ticketPool, customerRetrievalRate);
//        customerService.addVipCustomers(numVipCustomers, ticketPool, customerRetrievalRate);
    }


}

