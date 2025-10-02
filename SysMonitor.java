// SysMonitor.java
import java.lang.management.*;

public class SysMonitor {
    public static void main(String[] args) throws Exception {
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        Runtime rt = Runtime.getRuntime();
        while (true) {
            long usedMem = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
            System.out.println("CPU Load: " + os.getSystemLoadAverage() + " | Mem (MB): " + usedMem);
            Thread.sleep(1000);
        }
    }
}
