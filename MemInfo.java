import java.nio.file.*;

public class MemInfo {
    public static void main(String[] args) throws Exception {
        String data = Files.readString(Path.of("/proc/meminfo"));
        System.out.println(data);

        long memTotal = getValue(data, "MemTotal");
        long memFree  = getValue(data, "MemAvailable");
        System.out.println("\nUsed Memory: " + (memTotal - memFree)/1024 + " MB");
    }

    static long getValue(String data, String key){
        for (String line : data.split("\n"))
            if (line.startsWith(key))
                return Long.parseLong(line.replaceAll("\\D+", ""));
        return 0;
    }
}
