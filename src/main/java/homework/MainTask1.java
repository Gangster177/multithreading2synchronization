package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainTask1 {
    public static final char SYMBOL = 'R';
    //TODO Количество потоков равно количеству генерируемых маршрутов и равно 1000
    public static final int ROUTE = 10;

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
                System.out.println("Самое частое количество повторений " + count);
            };
            list.add(new Thread(runnable));
        }
        for (Thread thread : list) {
            thread.start();
        }
        for (Thread thread : list) {
            thread.join(); // зависаем, ждём когда поток, объект которого лежит в thread, завершится
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
