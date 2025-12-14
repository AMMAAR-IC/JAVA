import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class WebChangeDetector {
    public static void main(String[] args) throws Exception {
        URL u = new URL("https://example.com");
        byte[] data = u.openStream().readAllBytes();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(data);

        for (byte b : hash)
            System.out.printf("%02x", b);
    }
}
