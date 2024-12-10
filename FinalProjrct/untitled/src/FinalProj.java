/*Code created by Jonathan ortiz
*Purpose: Final project for se320
*/

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Question 1 and 2 Server Class
class BMI_Server implements Runnable {
    private Socket socket;

    // Constructor to initialize the socket
    public BMI_Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                // Create input and output streams for communication
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Receive weight and height from the client
            double weight = Double.parseDouble(in.readLine());
            double height = Double.parseDouble(in.readLine());

            // Calculate BMI using the provided formula
            double bmi = weight / (height * height);
            String result = "Your BMI is: " + bmi;

            // Send the result back to the client
            out.println(result);
        } catch (IOException | NumberFormatException e) {
            // Handle communication errors or invalid input
            System.out.println("Server error: " + e.getMessage());
        }
    }

    // Method to start the BMI server
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started. Waiting for clients...");
            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                // Handle each client in a separate thread
                new Thread(new BMI_Server(clientSocket)).start();
            }
        } catch (IOException e) {
            // Handle server socket creation or connection issues
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

// Question 1 Client Class
class BMI_Client {
    public static void startClient() {
        try (
                // Connect to the server
                Socket socket = new Socket("localhost", 12345);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            // Prompt user for weight and height
            System.out.println("Enter your weight (in kg): ");
            double weight = scanner.nextDouble();
            System.out.println("Enter your height (in meters): ");
            double height = scanner.nextDouble();

            // Send weight and height to the server
            out.println(weight);
            out.println(height);

            // Receive and display BMI result from the server
            System.out.println("Server response: " + in.readLine());
        } catch (IOException e) {
            // Handle connection or communication errors
            System.out.println("Client error: " + e.getMessage());
        }
    }
}

// Question 3 Class
class Question3 {
    // Generic method to perform linear search
    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
        try {
            // Iterate through the list to find the key
            for (int i = 0; i < list.length; i++) {
                if (list[i].compareTo(key) == 0) {
                    return i; // Return the index if key is found
                }
            }
        } catch (Exception e) {
            // Handle any runtime errors
            System.out.println("Error in linear search: " + e.getMessage());
        }
        return -1; // Return -1 if the key is not found
    }

    public static void testLinearSearch() {
        // Test linear search with an example array
        Integer[] list = {3, 4, 5, 1, -3, -5, -1};
        System.out.println(linearSearch(list, 2)); // Should return -1
        System.out.println(linearSearch(list, 5)); // Should return 2
    }
}

// Main Class with Switch Menu
public class FinalProj {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display menu
            System.out.println("\nAssignment Menu:");
            System.out.println("1. Start BMI Server");
            System.out.println("2. Start BMI Client");
            System.out.println("3. Test Linear Search");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                // Read user's choice
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline

                switch (choice) {
                    case 1:
                        // Start the BMI server
                        System.out.println("Starting BMI Server...");
                        BMI_Server.startServer();
                        break;
                    case 2:
                        // Start the BMI client
                        System.out.println("Starting BMI Client...");
                        BMI_Client.startClient();
                        break;
                    case 3:
                        // Test linear search
                        System.out.println("Testing Linear Search...");
                        Question3.testLinearSearch();
                        break;
                    case 4:
                        // Exit the program
                        System.out.println("Exiting...");
                        exit = true;
                        break;
                    default:
                        // Handle invalid menu options
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                // Handle invalid input (e.g., non-integer choices)
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close(); // Close the scanner resource
    }
}
