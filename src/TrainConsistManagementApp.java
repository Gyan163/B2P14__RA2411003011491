import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

public class TrainConsistManagementApp {

    // UC14
    static class InvalidCapacityException extends Exception {
        InvalidCapacityException(String msg) {
            super(msg);
        }
    }

    // UC15
    static class CargoSafetyException extends RuntimeException {
        CargoSafetyException(String msg) {
            super(msg);
        }
    }

    // UC7 + UC14
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Invalid capacity for " + name);
            }
            this.name = name;
            this.capacity = capacity;
        }

        public String toString() {
            return name + " (Capacity: " + capacity + ")";
        }
    }

    // UC12
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        public String toString() {
            return type + " -> " + cargo;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // UC1
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // UC2
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        System.out.println("\nAfter adding: " + trainConsist);

        trainConsist.remove("AC Chair");
        System.out.println("After removal: " + trainConsist);
        System.out.println("Sleeper exists? " + trainConsist.contains("Sleeper"));

        // UC3
        Set<String> ids = new HashSet<>();
        ids.add("B1");
        ids.add("B2");
        ids.add("B2");
        System.out.println("\nUnique IDs: " + ids);

        // UC4
        LinkedList<String> linked = new LinkedList<>();
        linked.add("Engine");
        linked.add("Sleeper");
        linked.add("AC");
        linked.add("Cargo");
        linked.add("Guard");

        linked.add(2, "Pantry Car");
        linked.removeFirst();
        linked.removeLast();
        System.out.println("\nLinked Train: " + linked);

        // UC5
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");
        System.out.println("\nOrdered Formation: " + formation);

        // UC6
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 40);

        System.out.println("\nCapacities:");
        for (Map.Entry<String, Integer> e : capacityMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // UC7 + UC14
        List<Bogie> bogies = new ArrayList<>();
        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("First Class", 40));
            bogies.add(new Bogie("Invalid", -5)); // exception
        } catch (InvalidCapacityException e) {
            System.out.println("\nException: " + e.getMessage());
        }

        System.out.println("\nValid Bogies: " + bogies);

        bogies.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("Sorted: " + bogies);

        // UC8
        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        System.out.println("\nFiltered (>60): " + filtered);

        // UC9
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> {
                    if (b.capacity > 60) return "High";
                    else if (b.capacity >= 50) return "Medium";
                    else return "Low";
                }));
        System.out.println("\nGrouped: " + grouped);

        // UC10
        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
        System.out.println("\nTotal Capacity: " + total);

        // UC11
        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println("\nTrain Valid: " + trainPattern.matcher("TRN-1234").matches());
        System.out.println("Cargo Valid: " + cargoPattern.matcher("PET-AB").matches());

        // UC12
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Box", "Grains"));

        boolean safe = goods.stream()
                .allMatch(b -> !(b.type.equals("Cylindrical") && !b.cargo.equals("Petroleum")));

        System.out.println("\nGoods: " + goods);
        System.out.println("Safe? " + safe);

        // UC13
        List<Bogie> test = new ArrayList<>();
        try {
            for (int i = 0; i < 10000; i++) {
                test.add(new Bogie("Sleeper", 72));
            }
        } catch (InvalidCapacityException e) {}

        long startLoop = System.nanoTime();
        List<Bogie> loopRes = new ArrayList<>();
        for (Bogie b : test) {
            if (b.capacity > 60) loopRes.add(b);
        }
        long loopTime = System.nanoTime() - startLoop;

        long startStream = System.nanoTime();
        List<Bogie> streamRes = test.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long streamTime = System.nanoTime() - startStream;

        System.out.println("\nLoop Time: " + loopTime);
        System.out.println("Stream Time: " + streamTime);

        // UC15
        try {
            String shape = "Rectangular";
            String cargo = "Petroleum";

            System.out.println("\nAssigning Cargo...");

            if (shape.equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new CargoSafetyException("Unsafe cargo assignment!");
            }

            System.out.println("Cargo assigned successfully.");

        } catch (CargoSafetyException e) {
            System.out.println("ERROR: " + e.getMessage());

        } finally {
            System.out.println("Cargo process completed.");
        }

        // UC16 (Bubble Sort)
        int[] capacities = {72, 60, 40, 90, 55};

        System.out.println("\nBefore Sorting (Bubble Sort):");
        System.out.println(Arrays.toString(capacities));

        for (int i = 0; i < capacities.length - 1; i++) {
            for (int j = 0; j < capacities.length - i - 1; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        System.out.println("After Sorting (Bubble Sort):");
        System.out.println(Arrays.toString(capacities));

        // UC17 (Arrays.sort)
        String[] bogieTypes = {"Sleeper", "AC Chair", "First Class", "Cargo", "Engine"};

        System.out.println("\nBefore Sorting (Arrays.sort):");
        System.out.println(Arrays.toString(bogieTypes));

        Arrays.sort(bogieTypes);

        System.out.println("After Sorting (Arrays.sort):");
        System.out.println(Arrays.toString(bogieTypes));

        // UC18 (Linear Search)
        String[] bogieIdsArr = {"B3", "B1", "B7", "B2", "B9"};
        String searchKey = "B7";

        System.out.println("\nSearching for Bogie ID: " + searchKey);

        boolean found = false;

        for (int i = 0; i < bogieIdsArr.length; i++) {
            if (bogieIdsArr[i].equals(searchKey)) {
                found = true;
                System.out.println("Bogie found at position: " + i);
                break;
            }
        }

        if (!found) {
            System.out.println("Bogie not found");
        }
    }
}