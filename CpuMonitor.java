import java.lang.management.*;

public class CpuMonitor {

    public static void main(String[] args){

        OperatingSystemMXBean os=
                ManagementFactory.getOperatingSystemMXBean();

        System.out.println("Processors: "+os.getAvailableProcessors());
        System.out.println("System Load: "+os.getSystemLoadAverage());
    }
}
