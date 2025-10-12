import java.io.FileInputStream;
import java.io.IOException;

public class FileTypeDetector {
    public static void main(String[] args) {
        String filePath = "test.pdf";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] bytes = new byte[4];
            fis.read(bytes);

            String hex = String.format("%02X%02X%02X%02X", bytes[0], bytes[1], bytes[2], bytes[3]);

            if (hex.startsWith("25504446")) {
                System.out.println("File is a PDF");
            } else if (hex.startsWith("89504E47")) {
                System.out.println("File is a PNG image");
            } else if (hex.startsWith("FFD8FF")) {
                System.out.println("File is a JPG image");
            } else {
                System.out.println("Unknown file type: " + hex);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
