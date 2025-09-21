import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class ResourceMonitor {
    public static void main(String[] args) throws InterruptedException {
        OperatingSystemMXBean osBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        while (true) {
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            long freeMem = osBean.getFreePhysicalMemorySize() / (1024 * 1024);
            long totalMem = osBean.getTotalPhysicalMemorySize() / (1024 * 1024);

            System.out.printf("CPU: %.2f%% | Free Mem: %dMB / %dMB%n",
                    cpuLoad, freeMem, totalMem);

            Thread.sleep(2000);
        }
    }
}
