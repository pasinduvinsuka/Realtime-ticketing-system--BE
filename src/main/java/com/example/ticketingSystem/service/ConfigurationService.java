package com.example.ticketingSystem.service;

import com.example.ticketingSystem.dto.ConfigurationDTO;
import com.example.ticketingSystem.util.InputValidator;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class ConfigurationService {

    private final ReentrantLock lock = new ReentrantLock();

    public int getMaxCapacity() {
        try {
            lock.lock();
            return maxCapacity;
        } finally {
            lock.unlock();
        }
    }

    public void setMaxCapacity(int maxCapacity) {
        try {
            lock.lock();
            this.maxCapacity = maxCapacity;
        } finally {
            lock.unlock();
        }
    }

    public int getTotalTickets() {
        try {
            lock.lock();
            return totalTickets;
        } finally {
            lock.unlock();
        }
    }

    public void setTotalTickets(int totalTickets) {
        try {
            lock.lock();
            this.totalTickets = totalTickets;
        } finally {
            lock.unlock();
        }
    }

    public int getNumVipCustomers() {
        try {
            lock.lock();
            return numVipCustomers;
        } finally {
            lock.unlock();
        }
    }

    public void setNumVipCustomers(int numVipCustomers) {
        try {
            lock.lock();
            this.numVipCustomers = numVipCustomers;
        } finally {
            lock.unlock();
        }
    }

    public int getNumRegularCustomers() {
        try {
            lock.lock();
            return numRegularCustomers;
        } finally {
            lock.unlock();
        }
    }

    public void setNumRegularCustomers(int numRegularCustomers) {
        try {
            lock.lock();
            this.numRegularCustomers = numRegularCustomers;
        } finally {
            lock.unlock();
        }
    }

    public int getNumberOfVendors() {
        try {
            lock.lock();
            return numberOfVendors;
        } finally {
            lock.unlock();
        }
    }

    public void setNumberOfVendors(int numberOfVendors) {
        try {
            lock.lock();
            this.numberOfVendors = numberOfVendors;
        } finally {
            lock.unlock();
        }
    }

    public int getTicketReleaseRate() {
        try {
            lock.lock();
            return ticketReleaseRate;
        } finally {
            lock.unlock();
        }
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        try {
            lock.lock();
            this.ticketReleaseRate = ticketReleaseRate;
        } finally {
            lock.unlock();
        }
    }

    public int getCustomerRetrievalRate() {
        try {
            lock.lock();
            return customerRetrievalRate;
        } finally {
            lock.unlock();
        }
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {

        try {
            lock.lock();
            this.customerRetrievalRate = customerRetrievalRate;
        } finally {
            lock.unlock();
        }

    }

    private int maxCapacity;
    private int totalTickets;
    private int numVipCustomers;
    private int numRegularCustomers;
    private int numberOfVendors;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    public void getConfigurations() {
        Scanner scanner = new Scanner(System.in);
        this.maxCapacity = InputValidator.validateInput(scanner, "Enter the max ticket capacity : ");
        this.totalTickets = InputValidator.validateInput(scanner, "Enter the total capacity:");
        this.numberOfVendors = InputValidator.validateInput(scanner, "Enter the number of vendors : ");
        this.numVipCustomers = InputValidator.validateInput(scanner, "Enter the number of VIP customers : ");
        this.numRegularCustomers = InputValidator.validateInput(scanner, "Enter the number of regular customers : ");
        this.ticketReleaseRate = InputValidator.validateInput(scanner, "Enter the ticket release rate : ");
        this.customerRetrievalRate = InputValidator.validateInput(scanner, "Enter the customer retrieval rate : ");
    }


    public void updateConfigurations(ConfigurationDTO config) {
        this.maxCapacity = config.getMaxCapacity();
        this.totalTickets = config.getTotalTickets();
        this.numVipCustomers = config.getNumVipCustomers();
        this.numRegularCustomers = config.getRegularCustomers();
        this.numberOfVendors = config.getNumberOfVendors();
        this.ticketReleaseRate = config.getTicketReleaseRate();
        this.customerRetrievalRate = config.getCustomerRetrievalRate();

    }


}

