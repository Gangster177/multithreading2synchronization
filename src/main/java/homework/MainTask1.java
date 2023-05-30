package homework;

import java.util.Random;

public class MainTask1 {
    public static void main(String[] args) {
        String result = generateRoute("RLRFR", 100);
        char symbol = 'R';
        int count = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == symbol){
                count++;
            }
        }
        System.out.println("Самое частое количество повторений " + count);

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
