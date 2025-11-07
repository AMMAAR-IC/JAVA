import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public class IntegrityMonitor {
    static Map<String,String> cache = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Path folder = Path.of("monitor");
        Files.createDirectories(folder);
        while (true) {
            Files.list(folder).filter(Files::isRegularFile).forEach(f -> {
                try {
                    String hash = sha256(f);
                    if (!hash.equals(cache.get(f.toString()))) {
                        System.out.println("Change detected in: " + f.getFileName());
                        cache.put(f.toString(), hash);
                    }
                } catch (Exception e) { e.printStackTrace(); }
            });
            Thread.sleep(3000);
        }
    }
    static String sha256(Path file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Files.readAllBytes(file));
        return Base64.getEncoder().encodeToString(md.digest());
    }
}
