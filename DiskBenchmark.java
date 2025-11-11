import java.io.*;
import java.nio.file.*;

public class DiskBenchmark {
    public static void main(String[] args) throws Exception {
        Path file = Path.of("test_speed.bin");
        byte[] data = new byte[100_000_000]; // 100MB
        long start = System.currentTimeMillis();
        Files.write(file, data);
        long write = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        Files.readAllBytes(file);
        long read = System.currentTimeMillis() - start;
        System.out.printf("Write: %.2f MB/s%nRead: %.2f MB/s%n", 100.0 / (write/1000.0), 100.0 / (read/1000.0));
    }
}
