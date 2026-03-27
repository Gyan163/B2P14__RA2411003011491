import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.LinkedHashSet;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // =====================================
        // UC1: Initialize Train Consist
        // =====================================

        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();

        System.out.println("Train initialized successfully.");
        System.out.println("Initial number of bogies: " + trainConsist.size());


        // =====================================
        // UC2: Add & Manage Passenger Bogies
        // =====================================

        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nAfter adding bogies:");
        System.out.println(trainConsist);

        trainConsist.remove("AC Chair");

        System.out.println("\nAfter removing AC Chair:");
        System.out.println(trainConsist);

        if (trainConsist.contains("Sleeper")) {
            System.out.println("\nSleeper bogie is present in the train.");
        } else {
            System.out.println("\nSleeper bogie is NOT present.");
        }

        System.out.println("\nFinal train consist:");
        System.out.println(trainConsist);


        // =====================================
        // UC3: Ensure Unique Bogie IDs (HashSet)
        // =====================================

        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("B1");
        bogieIds.add("B2");
        bogieIds.add("B3");
        bogieIds.add("B2"); // duplicate
        bogieIds.add("B1"); // duplicate

        System.out.println("\nUnique Bogie IDs (duplicates ignored):");
        System.out.println(bogieIds);


        // =====================================
        // UC4: LinkedList Train Operations
        // =====================================

        LinkedList<String> linkedTrain = new LinkedList<>();

        linkedTrain.add("Engine");
        linkedTrain.add("Sleeper");
        linkedTrain.add("AC");
        linkedTrain.add("Cargo");
        linkedTrain.add("Guard");

        System.out.println("\nInitial Linked Train:");
        System.out.println(linkedTrain);

        linkedTrain.add(2, "Pantry Car");

        System.out.println("\nAfter adding Pantry Car at position 2:");
        System.out.println(linkedTrain);

        linkedTrain.removeFirst();
        linkedTrain.removeLast();

        System.out.println("\nAfter removing first and last bogie:");
        System.out.println(linkedTrain);

        System.out.println("\nFinal Linked Train Consist:");
        System.out.println(linkedTrain);


        // =====================================
        // UC5: LinkedHashSet (Order + Uniqueness)
        // =====================================

        LinkedHashSet<String> formation = new LinkedHashSet<>();

        // Add bogies
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        // Attempt duplicate
        formation.add("Sleeper"); // will be ignored

        // Display formation
        System.out.println("\nFinal Train Formation (Ordered & Unique):");
        System.out.println(formation);

        // Program continues...
    }
}