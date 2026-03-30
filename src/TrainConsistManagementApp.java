import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

public class TrainConsistManagementApp {

    // UC7: Bogie class
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String toString() {
            return name + " (Capacity: " + capacity + ")";
        }
    }

    // UC12: Goods Bogie
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

        // ================= UC1 =================
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // ================= UC2 =================
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nAfter adding: " + trainConsist);

        trainConsist.remove("AC Chair");
        System.out.println("After removal: " + trainConsist);

        System.out.println("Sleeper exists? " + trainConsist.contains("Sleeper"));

        // ================= UC3 =================
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("B1");
        bogieIds.add("B2");
        bogieIds.add("B2");

        System.out.println("\nUnique IDs: " + bogieIds);

        // ================= UC4 =================
        LinkedList<String> linkedTrain = new LinkedList<>();
        linkedTrain.add("Engine");
        linkedTrain.add("Sleeper");
        linkedTrain.add("AC");
        linkedTrain.add("Cargo");
        linkedTrain.add("Guard");

        linkedTrain.add(2, "Pantry Car");
        linkedTrain.removeFirst();
        linkedTrain.removeLast();

        System.out.println("\nLinked Train: " + linkedTrain);

        // ================= UC5 =================
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("\nOrdered Unique Formation: " + formation);

        // ================= UC6 =================
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 40);

        System.out.println("\nBogie Capacities:");
        for (Map.Entry<String, Integer> e : capacityMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // ================= UC7 =================
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("\nSorted Bogies: " + bogies);

        // ================= UC8 =================
        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered (>60): " + filtered);

        // ================= UC9 =================
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> {
                    if (b.capacity > 60) return "High";
                    else if (b.capacity >= 50) return "Medium";
                    else return "Low";
                }));

        System.out.println("\nGrouped Bogies: " + grouped);

        // ================= UC10 =================
        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Capacity: " + total);

        // ================= UC11 =================
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println("\nTrain ID Valid: " + trainPattern.matcher(trainId).matches());
        System.out.println("Cargo Code Valid: " + cargoPattern.matcher(cargoCode).matches());

        // ================= UC12 =================
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Box", "Grains"));
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));

        boolean safe = goods.stream()
                .allMatch(b -> !(b.type.equals("Cylindrical") && !b.cargo.equals("Petroleum")));

        System.out.println("\nGoods: " + goods);
        System.out.println("Is Train Safe? " + safe);

        // ================= UC13 =================
        List<Bogie> test = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            test.add(new Bogie("Sleeper", 72));
            test.add(new Bogie("AC", 60));
            test.add(new Bogie("FC", 40));
        }

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
    }
}