import java.util.Random;

public class ChaosSimulator {
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(r.nextInt(500));
                        System.out.println(Thread.currentThread().getName() + ": " + r.nextInt(1000));
                    } catch (InterruptedException e) { }
                }
            }, "Chaos-" + i).start();
        }
    }
}
