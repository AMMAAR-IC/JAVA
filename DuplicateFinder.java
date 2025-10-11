// DuplicateFinder.java
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public class DuplicateFinder {
    public static void main(String[] args) throws Exception {
        Map<String, String> seen = new HashMap<>();
        Files.walk(Path.of("test")).filter(Files::isRegularFile).forEach(p -> {
            try {
                String hash = md5(Files.readAllBytes(p));
                if (seen.containsKey(hash))
                    System.out.println("Duplicate: " + p + " and " + seen.get(hash));
                else seen.put(hash, p.toString());
            } catch (Exception ignored) {}
        });
    }

    static String md5(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(data);
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
