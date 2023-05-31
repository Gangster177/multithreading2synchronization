package homework;

import java.util.*;

public class MainTask1 {
    public static final char SYMBOL = 'R';
    public static final int ROUTE = 1000;
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        String[] routes = new String[ROUTE];
        for (int i = 0; i < ROUTE; i++) {
            routes[i] = generateRoute("RLRFR", 100);
        }
        for (String route : routes) {
            Runnable runnable = () -> {
                int count = 0;
                for (int j = 0; j < route.length(); j++) {
                    if (route.charAt(j) == SYMBOL) {
                        count++;
                    }
                }
                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(count)) {
                        sizeToFreq.put(count, sizeToFreq.get(count) + 1);
                    } else {
                        sizeToFreq.put(count, 1);
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            list.add(thread);
        }
        for (Thread thread : list) {
            thread.join();
        }
        printMessage();
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static void printMessage() {
        Map.Entry<Integer, Integer> maxEntry = sizeToFreq.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);
        System.out.printf("Самое частое количество повторений %d (встретилось %d раз) \n", maxEntry.getKey(), maxEntry.getValue());
        sizeToFreq.remove(maxEntry.getKey());
        System.out.println("Другие размеры:");
        if (!maxEntry.equals(sizeToFreq)) {
            sizeToFreq.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue((a, b) -> b - a))
                    .forEach(e -> System.out.printf("- %d (%d раз)\n", e.getKey(), e.getValue()));
        }
    }
}
