import java.io.*;
import java.nio.file.*;
import java.security.*;

public class FileHash {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java FileHash <file>");
            return;
        }
        Path file = Paths.get(args[0]);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream in = Files.newInputStream(file)) {
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) md.update(buf, 0, n);
        }
        byte[] hash = md.digest();
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) hex.append(String.format("%02x", b));
        System.out.println(file + " : " + hex);
    }
}
