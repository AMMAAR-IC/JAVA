import java.util.Random;
import java.util.Scanner;

public class ReflexGame {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int score = 0;
        int timeLimit = 2000; // ms

        while (true) {
            char key = (char) ('A' + rand.nextInt(26));
            System.out.println("\nPress: " + key);
            long start = System.currentTimeMillis();

            Thread inputThread = new Thread(() -> {
                sc.nextLine();
            });
            inputThread.start();

            while (inputThread.isAlive() && System.currentTimeMillis() - start < timeLimit) {
                Thread.sleep(10);
            }

            if (inputThread.isAlive()) {
                inputThread.interrupt();
                System.out.println("⏰ Too slow! Final Score: " + score);
                break;
            } else {
                score++;
                System.out.println("✅ Good! Score: " + score);
                timeLimit = Math.max(400, timeLimit - 100); // Decrease time
            }
        }
        sc.close();
    }
}
