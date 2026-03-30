import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    // =====================================
    // UC7: Bogie Class
    // =====================================
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return name + " (Capacity: " + capacity + ")";
        }
    }

    public static void main(String[] args) {

        // =====================================
        // UC1
        // =====================================
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();

        System.out.println("Train initialized successfully.");
        System.out.println("Initial number of bogies: " + trainConsist.size());

        // =====================================
        // UC2
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
            System.out.println("\nSleeper bogie is present.");
        }

        System.out.println("\nFinal train consist:");
        System.out.println(trainConsist);

        // =====================================
        // UC3
        // =====================================
        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("B1");
        bogieIds.add("B2");
        bogieIds.add("B3");
        bogieIds.add("B2");

        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);

        // =====================================
        // UC4
        // =====================================
        LinkedList<String> linkedTrain = new LinkedList<>();

        linkedTrain.add("Engine");
        linkedTrain.add("Sleeper");
        linkedTrain.add("AC");
        linkedTrain.add("Cargo");
        linkedTrain.add("Guard");

        linkedTrain.add(2, "Pantry Car");

        linkedTrain.removeFirst();
        linkedTrain.removeLast();

        System.out.println("\nFinal Linked Train:");
        System.out.println(linkedTrain);

        // =====================================
        // UC5
        // =====================================
        LinkedHashSet<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("\nOrdered Unique Formation:");
        System.out.println(formation);

        // =====================================
        // UC6
        // =====================================
        Map<String, Integer> bogieCapacity = new HashMap<>();

        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 40);

        System.out.println("\nBogie Capacity:");

        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // =====================================
        // UC7
        // =====================================
        List<Bogie> bogieList = new ArrayList<>();

        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 60));
        bogieList.add(new Bogie("First Class", 40));

        System.out.println("\nBefore Sorting:");
        System.out.println(bogieList);

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nAfter Sorting:");
        System.out.println(bogieList);

        // =====================================
        // UC8
        // =====================================
        List<Bogie> filteredBogies = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        System.out.println(filteredBogies);

        // =====================================
        // UC9
        // =====================================
        Map<String, List<Bogie>> groupedBogies = bogieList.stream()
                .collect(Collectors.groupingBy(b -> {
                    if (b.capacity > 60) return "High Capacity";
                    else if (b.capacity >= 50) return "Medium Capacity";
                    else return "Low Capacity";
                }));

        System.out.println("\nGrouped Bogies:");

        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // =====================================
        // UC10: Aggregate Capacity using reduce()
        // =====================================

        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)      // extract capacities
                .reduce(0, Integer::sum);  // sum them

        System.out.println("\nTotal Seating Capacity:");
        System.out.println(totalCapacity);

        // Program continues...
    }
}