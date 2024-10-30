import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;

public class proj3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display a menu for the user to choose an option
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Perform Linked Hash Set Operations (Union, Difference, Intersection)");
            System.out.println("2. Read Nonduplicate Words in Ascending Order from a File");
            System.out.println("3. Format Number in UK Locale");
            System.out.println("4. Format Number as U.S. Currency");
            System.out.println("5. Parse '12,345.678' into a Number");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            // Execute the selected option using a switch statement
            switch (choice) {
                case 1:
                    linkedHashSetOperations();
                    break;
                case 2:
                    readNonDuplicateWords();
                    break;
                case 3:
                    formatNumberUKLocale();
                    break;
                case 4:
                    formatNumberUSCurrency();
                    break;
                case 5:
                    parseNumber();
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to perform union, difference, and intersection on two sets
    public static void linkedHashSetOperations() {
        // Create two linked hash sets with different elements
        Set<String> set1 = new LinkedHashSet<>();
        set1.add("George");
        set1.add("Jim");
        set1.add("John");
        set1.add("Blake");
        set1.add("Kevin");
        set1.add("Michael");

        Set<String> set2 = new LinkedHashSet<>();
        set2.add("George");
        set2.add("Katie");
        set2.add("Kevin");
        set2.add("Michelle");
        set2.add("Ryan");

        // Union: combine elements from both sets
        Set<String> unionSet = new LinkedHashSet<>(set1);
        unionSet.addAll(set2);
        System.out.println("Union: " + unionSet);

        // Difference: elements in set1 that are not in set2
        Set<String> differenceSet = new LinkedHashSet<>(set1);
        differenceSet.removeAll(set2);
        System.out.println("Difference: " + differenceSet);

        // Intersection: elements common to both sets
        Set<String> intersectionSet = new LinkedHashSet<>(set1);
        intersectionSet.retainAll(set2);
        System.out.println("Intersection: " + intersectionSet);
    }

    // Method to read words from a file and display them in sorted, non-duplicate order
    public static void readNonDuplicateWords() {
        // Use a TreeSet to store words (automatically sorted and unique)
        TreeSet<String> words = new TreeSet<>();

        try (Scanner fileScanner = new Scanner(new File("textfile.txt"))) {
            // Read words from file, convert to lowercase, and add to set
            while (fileScanner.hasNext()) {
                String word = fileScanner.next();
                words.add(word.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        System.out.println("Nonduplicate words in ascending order: " + words);
    }

    // Method to format a number in the UK locale with two decimal places
    public static void formatNumberUKLocale() {
        double number = 12345.678;
        NumberFormat ukFormat = NumberFormat.getInstance(Locale.UK);
        ukFormat.setMaximumFractionDigits(2);
        ukFormat.setMinimumFractionDigits(2);

        System.out.println("Formatted Number (UK): " + ukFormat.format(number));
    }

    // Method to format a number in U.S. currency format
    public static void formatNumberUSCurrency() {
        double number = 12345.678;
        NumberFormat usCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Formatted Number (US Currency): " + usCurrencyFormat.format(number));
    }

    // Method to parse a string representing a number in U.S. format
    public static void parseNumber() {
        String numberString = "12,345.678";
        NumberFormat format = NumberFormat.getInstance(Locale.US);

        try {
            // Parse the string into a Number object
            Number number = format.parse(numberString);
            System.out.println("Parsed Number: " + number);
        } catch (ParseException e) {
            System.out.println("Error parsing number.");
        }
    }
}
