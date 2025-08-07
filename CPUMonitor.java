import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class CPUMonitor {
    public static void main(String[] args) throws InterruptedException {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        System.out.println("🔍 Monitoring CPU usage. Press Ctrl+C to stop.\n");

        while (true) {
            double load = osBean.getSystemCpuLoad() * 100;
            System.out.printf("CPU Usage: %.2f%%\r", load);
            Thread.sleep(1000);
        }
    }
}
