/*
Jonathan Ortiz
Purpose
 */

 */
 */
 */
import java.util.scanner;


public class Project2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Main loop to keep the program running until the user decides to quit
        while (!exit) {
            // Display the menu options
            System.out.println("Please choose an option:");
            System.out.println("1. Enter a number between 0 and 10");
            System.out.println("2. Remove duplicates from a list (Exercise19_03)");
            System.out.println("3. Quit");

            // Get the user's choice
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue; // Skip the rest of the loop and show the menu again
            }

            // Handle the user's choice
            switch (choice) {
                case 1:
                    enterNumberBetween0And10(scanner);
                    break;
                case 2:
                    new Main().Exercise19_03();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }

        // Close the scanner to avoid resource leak
        scanner.close();
    }

    // Method for option 1: Prompt the user to enter a number between 0 and 10
    public static void enterNumberBetween0And10(Scanner scanner) {
        int number = -1; // Initialize with an invalid value to enter the loop

        // Continuously prompt the user until a valid number is entered
        while (true) {
            System.out.print("Enter a number between 0 and 10: ");

            try {
                number = scanner.nextInt();

                // Manually throw an AssertionError if the number is out of range
                if (number < 0 || number > 10) {
                    throw new AssertionError("The entered number is out of range");
                }

                // If no exception is thrown, break out of the loop
                break;

            } catch (AssertionError e) {
                // Handle the assertion error and display the custom error message
                System.err.println(e.getMessage());
            } catch (Exception e) {
                // Handle invalid input types (e.g., non-integer values)
                System.err.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Display the valid entered number
        System.out.println("You entered: " + number);
    }

    // Method for option 2: Exercise19_03 to remove duplicates from a list
    public void Exercise19_03() {
        // Create an ArrayList with duplicates
        ArrayList<Integer> list = new ArrayList<>();
        list.add(14);
        list.add(24);
        list.add(14);
        list.add(42);
        list.add(25);

        // Remove duplicates using the method
        ArrayList<Integer> newList = removeDuplicates(list);

        // Print the new list without duplicates
        System.out.println("List after removing duplicates: " + newList);
    }

    // Method to remove duplicates from an ArrayList
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        // Create a HashSet to track seen elements
        HashSet<E> seen = new HashSet<>();
        // Create a new ArrayList to store the result without duplicates
        ArrayList<E> result = new ArrayList<>();

        // Iterate through each element in the input list
        for (E element : list) {
            // If the element is not in the set, add it to the result and the set
            if (seen.add(element)) {
                result.add(element);
            }
        }

        return result;
    }
}

}
