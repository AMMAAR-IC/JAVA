import java.net.*;
import java.util.concurrent.*;

public class JarvisNetScanner {
    public static void main(String[] args) throws Exception {
        String subnet = "192.168.1."; // Customize your local subnet
        ExecutorService executor = Executors.newFixedThreadPool(50);
        System.out.println("Scanning...");

        for (int i = 1; i < 255; i++) {
            final int j = i;
            executor.submit(() -> {
                try {
                    String ip = subnet + j;
                    InetAddress inet = InetAddress.getByName(ip);
                    long start = System.currentTimeMillis();
                    if (inet.isReachable(100)) {
                        long ping = System.currentTimeMillis() - start;
                        System.out.println(ip + " is online (" + ping + " ms)");
                    }
                } catch (Exception ignored) {}
            });
        }
        executor.shutdown();
    }
}
