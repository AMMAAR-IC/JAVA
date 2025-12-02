import java.nio.file.*;

public class CpuUsage {
    public static void main(String[] args) throws Exception {
        long[] a = read();
        Thread.sleep(1000);
        long[] b = read();
        long idleDiff = b[3]-a[3];
        long totalDiff = sum(b)-sum(a);
        double usage = 100.0 * (totalDiff-idleDiff)/totalDiff;
        System.out.printf("CPU Usage: %.2f%%%n", usage);
    }

    static long[] read() throws Exception {
        String[] parts = Files.readString(Path.of("/proc/stat")).split("\\s+");
        long[] vals = new long[8];
        for (int i=0;i<8;i++) vals[i]=Long.parseLong(parts[i+2]);
        return vals;
    }

    static long sum(long[] x){ long s=0; for(long v:x)s+=v; return s; }
}
