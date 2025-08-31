import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class SystemMonitor {
    public static void main(String[] args) throws InterruptedException {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        
        while (true) {
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            long freeMemory = osBean.getFreePhysicalMemorySize() / (1024 * 1024);
            long totalMemory = osBean.getTotalPhysicalMemorySize() / (1024 * 1024);

            System.out.printf("CPU Load: %.2f%% | Memory: %dMB / %dMB free%n", cpuLoad, freeMemory, totalMemory);
            Thread.sleep(2000);
        }
    }
}
