import java.nio.file.*;
import java.util.Base64;

public class ImageToBase64 {
    public static void main(String[] args) throws Exception {
        byte[] img = Files.readAllBytes(Path.of("logo.png"));
        String b64 = Base64.getEncoder().encodeToString(img);
        System.out.println("data:image/png;base64," + b64);
    }
}
