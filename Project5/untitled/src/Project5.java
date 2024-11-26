/*
Jonathan Ortiz
Purpose: Project 5 for SE320
 */
import java.util.*;

public class Project5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display menu options
        System.out.println("Choose an option:");
        System.out.println("1: Calculate the number of days since your birth.");
        System.out.println("2: Test the removeDuplicates method.");
        int choice = scanner.nextInt();

        // Handle user choice
        switch (choice) {
            case 1:
                // Option 1: Calculate days since birth
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
                break;

            case 2:
                // Option 2: Test removeDuplicates
                // Example list with duplicates
                List<String> list = new ArrayList<>(Arrays.asList("a", "b", "b", "c", "c", "c", "d"));
                System.out.println("Original list: " + list);

                // Remove duplicates and display the result
                removeDuplicates(list);
                System.out.println("List after removing duplicates: " + list);
                break;

            default:
                // Handle invalid input
                System.out.println("Invalid choice. Please select either 1 or 2.");
        }
    }

    /**
     * Removes duplicates from a sorted list.
     * Precondition: The list must not be null, and its elements must be sorted.
     *
     * @param lst the list from which duplicates will be removed
     */
    public static void removeDuplicates(List lst) {
        if (lst == null || lst.size() == 0) return; // Handle null or empty list

        // Create a copy of the list to iterate over
        List copy = new ArrayList(lst);
        Iterator elements = copy.iterator();
        Object pre = elements.next();

        // Iterate through the list and remove duplicates
        while (elements.hasNext()) {
            Object nex = elements.next();
            if (pre.equals(nex)) lst.remove(nex); // Remove duplicate element
            else pre = nex; // Update the previous element
        }
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
     */
    public Day(int year, int month, int day) {
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