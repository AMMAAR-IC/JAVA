import java.util.*;

public class EarthquakeSim {
    public static void main(String[] args) throws InterruptedException {
        String msg = "⚡ EARTHQUAKE DETECTED ⚡";
        Random rand = new Random();

        while (true) {
            int spaces = rand.nextInt(10);
            System.out.print("\033[H\033[2J"); 
            System.out.flush();
            System.out.println(" ".repeat(spaces) + msg);
            Thread.sleep(100);
        }
    }
}
