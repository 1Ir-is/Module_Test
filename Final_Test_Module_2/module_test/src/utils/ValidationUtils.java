package utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String validateInput(String regex, String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter again:");
                continue;
            }
            if (Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    public static boolean validateNotEmpty(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    public static boolean validateDateFormat(String date, String errorMessage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(date, formatter);
            return false;
        } catch (Exception e) {
            System.out.println(errorMessage);
            return true;
        }
    }

    public static boolean validateStartBeforeEnd(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            if (start.isBefore(end)) {
                return true;
            } else {
                System.out.println("Start date must be earlier than end date!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date. Please enter in right format!");
            return false;
        }
    }


    public static int validatePositiveInt(String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter again:");
                continue;
            }
            try {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again!");
            }
        }
    }

    public static int validateMenuChoice(int max) {
        int choice;
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a number between " + 1 + " and " + max + ":");
                    continue;
                }
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + 1 + " and " + max + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again!");
            }
        }
    }
}