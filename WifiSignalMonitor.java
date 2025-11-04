import java.nio.file.*;

public class WifiSignalMonitor {
    public static void main(String[] args) throws Exception {
        while (true) {
            String data = Files.readString(Path.of("/proc/net/wireless"));
            String[] lines = data.split("\n");
            if (lines.length > 2) {
                String[] parts = lines[2].trim().split("\\s+");
                System.out.println("Interface: " + parts[0]);
                System.out.println("Signal Level: " + parts[3] + " dBm");
            }
            Thread.sleep(3000);
        }
    }
}
