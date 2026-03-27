import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // =====================================
        // UC1: Initialize Train Consist
        // =====================================

        System.out.println("=== Train Consist Management App ===");

        // Initialize empty train consist
        List<String> trainConsist = new ArrayList<>();

        System.out.println("Train initialized successfully.");
        System.out.println("Initial number of bogies: " + trainConsist.size());


        // =====================================
        // UC2: Add & Manage Passenger Bogies
        // =====================================

        // Add bogies
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        // Display after adding
        System.out.println("\nAfter adding bogies:");
        System.out.println(trainConsist);

        // Remove a bogie
        trainConsist.remove("AC Chair");

        // Display after removal
        System.out.println("\nAfter removing AC Chair:");
        System.out.println(trainConsist);

        // Check if Sleeper exists
        if (trainConsist.contains("Sleeper")) {
            System.out.println("\nSleeper bogie is present in the train.");
        } else {
            System.out.println("\nSleeper bogie is NOT present.");
        }

        // Final state
        System.out.println("\nFinal train consist:");
        System.out.println(trainConsist);

        // Program continues...
    }
}