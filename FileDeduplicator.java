import java.nio.file.*;
import java.security.*;
import java.util.*;

public class FileDeduplicator {
    public static void main(String[] args) throws Exception {
        Map<String, List<Path>> map = new HashMap<>();

        for (Path p : Files.list(Path.of(".")).toList()) {
            if (!Files.isRegularFile(p)) continue;
            String hash = hash(Files.readAllBytes(p));
            map.computeIfAbsent(hash, k -> new ArrayList<>()).add(p);
        }

        map.values().stream()
                .filter(l -> l.size() > 1)
                .forEach(System.out::println);
    }

    static String hash(byte[] b) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] h = md.digest(b);
        StringBuilder sb = new StringBuilder();
        for (byte x : h) sb.append(String.format("%02x", x));
        return sb.toString();
    }
}
