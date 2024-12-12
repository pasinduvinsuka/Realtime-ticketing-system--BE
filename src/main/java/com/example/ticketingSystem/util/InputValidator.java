package com.example.ticketingSystem.util;

import java.util.Scanner;

public class InputValidator {
    public static int validateInput(Scanner sc, String message) {
        int value;
        System.out.println(message);
        while (true) {
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive integer (greater than zero):");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer:");
                sc.next(); // Consume the invalid input
            }
        }
    }
}
