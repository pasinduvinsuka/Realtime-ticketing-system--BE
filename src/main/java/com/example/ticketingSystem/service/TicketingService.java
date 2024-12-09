package com.example.ticketingSystem.service;

import com.example.ticketingSystem.model.Customer;
import com.example.ticketingSystem.model.CustomerPriority;
import com.example.ticketingSystem.model.TicketPool;
import com.example.ticketingSystem.model.Vendor;
import com.example.ticketingSystem.util.InputValidator;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@Service
public class TicketingService {


    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the max ticket capacity : ");
        int maxCapacity = sc.nextInt();

        System.out.println("Enter the number of vendors : ");
        int numVipCustomers = sc.nextInt();

        System.out.println("Enter the number of VIP customers : ");
        int regularCustomers = sc.nextInt();

        System.out.println("Enter the number of regular customers : ");
        int numberOfVendors = sc.nextInt();

        try (ExecutorService ticketExecutorService = Executors.newCachedThreadPool()) {
            PriorityBlockingQueue<CustomerPriority> customerQueue = new PriorityBlockingQueue<>();
            TicketPool ticketPool = new TicketPool(maxCapacity, 2);

            //add 2 vendors to the thread pool
            for (int i = 0; i < numberOfVendors; i++) {
                ticketExecutorService.execute(new Vendor(i, ticketPool));
            }
            //create 2 non-vip customers
            for (int i = 0; i < regularCustomers; i++) {
                Customer customer = new Customer(i, false, ticketPool);
                customerQueue.add(new CustomerPriority(customer, false));
            }

            //create 2 vip customers
            for (int i = 0; i < numVipCustomers; i++) {
                Customer customer = new Customer(i, true, ticketPool);
                customerQueue.add(new CustomerPriority(customer, true));
            }

            while (!customerQueue.isEmpty()) {
                ticketExecutorService.execute(customerQueue.poll().getCustomer());
            }

            ticketExecutorService.shutdown();

        }

    }

}

