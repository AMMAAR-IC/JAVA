import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class IrisRecognition {

    // Convert image to a simple binary iris code
    public static String extractIrisCode(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        StringBuilder code = new StringBuilder();

        // Downsample to 16x16 grid
        for (int y = 0; y < height; y += height / 16) {
            for (int x = 0; x < width; x += width / 16) {
                int rgb = img.getRGB(x, y) & 0xFF;
                code.append(rgb > 128 ? "1" : "0");
            }
        }
        return code.toString();
    }

    // Compare two iris codes using Hamming Distance
    public static double compareIrisCodes(String code1, String code2) {
        int dist = 0;
        for (int i = 0; i < code1.length(); i++) {
            if (code1.charAt(i) != code2.charAt(i)) dist++;
        }
        return 100 - (dist * 100.0 / code1.length());
    }

    public static void main(String[] args) throws Exception {
        BufferedImage iris1 = ImageIO.read(new File("iris1.jpg"));
        BufferedImage iris2 = ImageIO.read(new File("iris2.jpg"));

        String code1 = extractIrisCode(iris1);
        String code2 = extractIrisCode(iris2);

        System.out.println("Iris Code 1: " + code1);
        System.out.println("Iris Code 2: " + code2);

        double similarity = compareIrisCodes(code1, code2);
        System.out.println("🔎 Iris Match Similarity: " + similarity + "%");

        if (similarity > 85) {
            System.out.println("✅ Same Person (Iris Verified).");
        } else {
            System.out.println("⚠️ Iris Mismatch (Different Person).");
        }
    }
}
