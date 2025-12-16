import java.lang.management.*;

public class ResourceWatchdog {
    public static void main(String[] args) throws Exception {
        OperatingSystemMXBean os =
                ManagementFactory.getOperatingSystemMXBean();

        while (true) {
            long usedMem = Runtime.getRuntime().totalMemory()
                    - Runtime.getRuntime().freeMemory();
            System.out.println("Memory: " + usedMem / 1024 / 1024 + " MB");
            Thread.sleep(1000);
        }
    }
}
