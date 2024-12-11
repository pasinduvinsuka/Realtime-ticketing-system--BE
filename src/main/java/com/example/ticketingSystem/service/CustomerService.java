package com.example.ticketingSystem.service;

import com.example.ticketingSystem.model.Customer;
import com.example.ticketingSystem.model.CustomerPriority;
import com.example.ticketingSystem.model.TicketPool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

@Service
public class CustomerService {
    
    public PriorityBlockingQueue<CustomerPriority> getCustomerQueue() {
        return customerQueue;
    }

    private final PriorityBlockingQueue<CustomerPriority> customerQueue = new PriorityBlockingQueue<>();

    //add regular customers to the queue
    public void addRegularCustomers(int count, TicketPool ticketPool, int customerRetrievalRate) {
        for (int i = 0; i < count; i++) {
            Customer customer = new Customer(i, false, ticketPool, customerRetrievalRate);
            customerQueue.add(new CustomerPriority(customer, false));
        }
    }

    //add vip customers to the queue
    public void addVipCustomers(int count, TicketPool ticketPool, int customerRetrievalRate) {
        for (int i = 0; i < count; i++) {
            Customer customer = new Customer(i, true, ticketPool, customerRetrievalRate);
            customerQueue.add(new CustomerPriority(customer, true));
        }
    }

}
