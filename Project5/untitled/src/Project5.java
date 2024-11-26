/*
Jonathan Ortiz
Purpose: Project 5 for SE320
 */
import java.util.*;

public class Project5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Display menu options
            System.out.println("Choose an option:");
            System.out.println("1: Calculate the number of days since your birth.");
            System.out.println("2: Test the removeDuplicates method.");
            int choice = scanner.nextInt();

            // Handle user choice
            switch (choice) {
                case 1:
                    handleBirthDateCalculation(scanner);
                    break;
                case 2:
                    handleRemoveDuplicates();
                    break;
                default:
                    System.out.println("Invalid choice. Please select either 1 or 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid input.");
        } finally {
            scanner.close();
        }
    }

    /**
     * Handles Option 1: Calculate the number of days since birth.
     *
     * @param scanner the Scanner object for user input
     */
    private static void handleBirthDateCalculation(Scanner scanner) {
        try {
            System.out.println("Enter your birth year: ");
            int year = scanner.nextInt();
            System.out.println("Enter your birth month (1-12): ");
            int month = scanner.nextInt();
            System.out.println("Enter your birth day (1-31): ");
            int day = scanner.nextInt();

            // Create Day objects for birthdate and today's date
            Day birthDate = new Day(year, month, day);
            Day currentDate = Day.today();

            // Calculate and display the number of days elapsed
            int daysElapsed = birthDate.daysFrom(currentDate);
            System.out.println("Number of days since your birth: " + Math.abs(daysElapsed));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles Option 2: Remove duplicates from a sorted list.
     */
    private static void handleRemoveDuplicates() {
        try {
            // Example list with duplicates
            List<String> list = new ArrayList<>(Arrays.asList("a", "b", "b", "c", "c", "c", "d"));
            System.out.println("Original list: " + list);

            // Remove duplicates and display the result
            removeDuplicates(list);
            System.out.println("List after removing duplicates: " + list);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Removes duplicates from a sorted list.
     * Precondition: The list must not be null, and its elements must be sorted.
     *
     * @param lst the list from which duplicates will be removed
     * @throws IllegalArgumentException if the input list is null or not sorted
     */
    public static <T extends Comparable<T>> void removeDuplicates(List<T> lst) {
        if (lst == null) {
            throw new IllegalArgumentException("The list cannot be null.");
        }

        if (!isSorted(lst)) {
            throw new IllegalArgumentException("The list must be sorted to remove duplicates.");
        }

        try {
            Iterator<T> iterator = lst.iterator();
            if (!iterator.hasNext()) return;

            T previous = iterator.next();
            while (iterator.hasNext()) {
                T current = iterator.next();
                if (previous.equals(current)) {
                    iterator.remove(); // Safely remove the current element
                } else {
                    previous = current; // Update the previous element
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Error: Concurrent modification detected. Ensure no other thread modifies the list.");
        }
    }

    /**
     * Checks if a list is sorted in ascending order.
     *
     * @param lst the list to check
     * @return true if the list is sorted, false otherwise
     */
    private static <T extends Comparable<T>> boolean isSorted(List<T> lst) {
        for (int i = 1; i < lst.size(); i++) {
            if (lst.get(i).compareTo(lst.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }
}

/**
 * A simple Day class for managing dates and calculating elapsed days.
 */
class Day {
    private int year;
    private int month;
    private int day;

    /**
     * Constructs a Day object with the specified year, month, and day.
     *
     * @param year  the year
     * @param month the month (1-12)
     * @param day   the day (1-31)
     * @throws IllegalArgumentException if any input is invalid
     */
    public Day(int year, int month, int day) {
        if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid date: Year, month, or day out of range.");
        }

        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Creates a Day object representing today's date.
     *
     * @return a Day object for the current date
     */
    public static Day today() {
        Calendar calendar = Calendar.getInstance();
        return new Day(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Calculates the number of days between this Day and another Day.
     *
     * @param other the other Day object
     * @return the number of days from the other Day
     */
    public int daysFrom(Day other) {
        return Math.toIntExact(toEpochDay() - other.toEpochDay());
    }

    /**
     * Converts the date to the number of days since 1970-01-01.
     *
     * @return the epoch day representation of the date
     */
    private long toEpochDay() {
        int y = year;
        int m = month;

        // Adjust for months January and February
        if (m <= 2) {
            y--;
            m += 12;
        }

        long era = (long) y / 400;
        long yoe = y - era * 400; // year-of-era
        long doy = (153 * (m + 1) / 5) + day - 123;
        long doe = yoe * 365 + yoe / 4 - yoe / 100 + doy;

        return era * 146097 + doe - 719468; // Days from 1970-01-01
    }
}
