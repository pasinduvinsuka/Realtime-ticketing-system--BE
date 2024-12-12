package com.example.ticketingSystem.model;

import com.example.ticketingSystem.controller.TicketController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);
    private final Queue<String> tickets;
    private final int maxTicketCapacity;
    private final TicketController ticketController;
    ReentrantLock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();

    private int vipCount = 0;
    private int nonVipCount = 0;

    public TicketPool(int maxTicketCapacity, int totalTickets, TicketController ticketController) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.ticketController = ticketController;
        tickets = new ConcurrentLinkedQueue<>();
        if (totalTickets < maxTicketCapacity) {
            try {
                lock.lock();
                while (tickets.size() >= maxTicketCapacity) {
                    notFull.await();
                }
                for (int i = 0; i < totalTickets; i++) {
                    tickets.add("Initial Tickets");
                }
                notEmpty.signalAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    //method to add tickets into the pool
    public void addTicket(String ticket, int ticketReleaseRate) {
        try {
            lock.lock();
            while (tickets.size() >= maxTicketCapacity) {
                logger.info("Pool full. Vendor waiting...");
                notFull.await();
            }

            for (int i = 0; i < ticketReleaseRate; i++) {
                if (tickets.size() < maxTicketCapacity) {
                    tickets.add(ticket);
                } else {
                    logger.info("Max capacity reached, can't add more tickets.");
                    break;
                }
            }

            logger.info("Total tickets after a add :{}", tickets.size());

            // Send real-time update to clients
            ticketController.getTicketDetails(tickets, vipCount, nonVipCount);

            notEmpty.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }


    }

    //method to remove the ticket
    public String removeTicket(boolean isVIP) {
        try {
            lock.lock();
            while (tickets.isEmpty()) {
                logger.info("Pool empty. Customer waiting...");
                notEmpty.await();

            }
            String removedTicket = tickets.poll();
            logger.info("Total tickets after a buy :{}", tickets.size());
            logger.info("Bought by a  :{}", isVIP ? "VIP customer" : "regular customer");

            if (isVIP) {
                vipCount++;
            } else {
                nonVipCount++;
            }
            logger.info("Total VIP purchases: {}", vipCount);
            logger.info("Total regular purchases: {}", nonVipCount);

            ticketController.getTicketDetails(tickets, vipCount, nonVipCount);
            notFull.signalAll();
            return removedTicket;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return "";
    }


}
