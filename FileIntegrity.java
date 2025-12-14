import java.nio.file.*;
import java.security.*;

public class FileIntegrity {
    public static void main(String[] args) throws Exception {
        Path p = Path.of("test.txt");
        byte[] b = Files.readAllBytes(p);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] h = md.digest(b);

        for (byte x : h)
            System.out.printf("%02x", x);
    }
}
