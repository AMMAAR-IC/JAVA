import java.nio.file.*;
import java.security.*;

public class FileHashVerify {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Path.of("file.txt"));
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(data);

        for (byte b : hash)
            System.out.printf("%02x", b);
    }
}
