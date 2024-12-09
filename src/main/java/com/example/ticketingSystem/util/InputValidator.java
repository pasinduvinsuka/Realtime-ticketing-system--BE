package com.example.ticketingSystem.util;

import java.util.Scanner;

public class  InputValidator {
    private static int value = -1;

    public static int  validateInput(Scanner sc, String message) {
        System.out.println(message);
        while (true) {
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive value");
                }

            } else {
                System.out.println("Invalid Input");
            }
        }
    }
}
