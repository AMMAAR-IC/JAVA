import java.nio.file.*;

public class NetGraph {
    public static void main(String[] args) throws Exception {
        long prevRx = rx();
        while (true) {
            long now = rx();
            long diff = now - prevRx;
            prevRx = now;

            int bars = (int)(diff / 10240); // ~10KB = 1 bar
            System.out.println("█".repeat(Math.min(bars, 80)) + " " + diff / 1024 + " KB/s");

            Thread.sleep(1000);
        }
    }

    static long rx() throws Exception {
        for (String l : Files.readAllLines(Path.of("/proc/net/dev"))) {
            if (l.contains("wlan") || l.contains("eth")) {
                String[] p = l.split(":")[1].trim().split("\\s+");
                return Long.parseLong(p[0]);
            }
        }
        return 0;
    }
}
