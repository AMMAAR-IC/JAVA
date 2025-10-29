import java.io.File;

public class DiskMonitor {
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.printf("Drive: %s  Free: %.2f GB  Total: %.2f GB%n",
                root, root.getFreeSpace()/1e9, root.getTotalSpace()/1e9);
        }
    }
}
