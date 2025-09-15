// FileIntegrityChecker.java
import java.io.*;
import java.security.*;

public class FileIntegrityChecker {
    public static String getHash(File f) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream is = new FileInputStream(f)) {
            byte[] buf = new byte[8192];
            int n;
            while ((n = is.read(buf)) > 0) md.update(buf, 0, n);
        }
        byte[] hash = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        File f = new File(args.length > 0 ? args[0] : "test.txt");
        System.out.println("SHA-256: " + getHash(f));
    }
}
