import java.io.*;
import java.net.*;

public class BandwidthMeter {
    public static void main(String[] args) throws Exception {
        URL u = new URL("https://speed.hetzner.de/10MB.bin");
        long start = System.currentTimeMillis();
        try (InputStream in = u.openStream()) {
            byte[] buf = new byte[8192]; int total = 0, n;
            while ((n = in.read(buf)) != -1) total += n;
            long end = System.currentTimeMillis();
            double sec = (end - start) / 1000.0;
            System.out.printf("Speed: %.2f MB/s%n", (total / 1e6) / sec);
        }
    }
}
