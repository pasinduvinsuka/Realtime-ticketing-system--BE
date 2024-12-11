package com.example.ticketingSystem.model;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class TicketPool {
    private final Queue<String> tickets;
    private final int maxTicketCapacity;
    private final int totalTickets;
    ReentrantLock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();
    private final SimpMessagingTemplate simpMessagingTemplate;  // For WebSocket messaging

    public TicketPool(int maxTicketCapacity, int totalTickets, SimpMessagingTemplate simpMessagingTemplate) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        tickets = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < this.totalTickets; i++) {
            tickets.add("Initial Ticket " + i);
        }
        this.simpMessagingTemplate = simpMessagingTemplate;

    }

    //method to add tickets into the pool
    public void addTicket(String ticket, int ticketReleaseRate) {
        try {
            lock.lock();
            while (tickets.size() == maxTicketCapacity) {
                System.out.println("Pool full. Vendor waiting...");
                notFull.await();
            }
            for (int i = 0; i < ticketReleaseRate; i++) {
                tickets.add(ticket);
            }
            System.out.println("Total tickets after a add : " + tickets.size());

            // Send real-time update to clients
            sendTicketCountUpdate();

            notEmpty.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }


    }

    //method to remove the ticket
    public String removeTicket() {
        try {
            lock.lock();
            while (tickets.isEmpty()) {
                System.out.println("Pool empty. Customer waiting...");
                notEmpty.await();
            }
            String removedTicket = tickets.poll();
            System.out.println("Total tickets after a buy: " + tickets.size());
            sendTicketCountUpdate();
            notFull.signalAll();
            return removedTicket;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }


    private void sendTicketCountUpdate() {
        int currentTicketCount = tickets.size();
        simpMessagingTemplate.convertAndSend("/topic/tickets", currentTicketCount);
    }
}
