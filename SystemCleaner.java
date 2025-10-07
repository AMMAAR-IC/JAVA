// SystemCleaner.java
import java.io.*;
import java.nio.file.*;

public class SystemCleaner {
    public static void main(String[] args) throws IOException {
        Path tempDir = Path.of(System.getProperty("java.io.tmpdir"));
        long deleted = Files.walk(tempDir)
                .filter(Files::isRegularFile)
                .peek(p -> p.toFile().delete())
                .count();
        System.out.println("🧹 Deleted " + deleted + " temp files from " + tempDir);
    }
}
