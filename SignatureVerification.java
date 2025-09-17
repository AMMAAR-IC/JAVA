import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SignatureVerification {

    public static double compareSignatures(BufferedImage img1, BufferedImage img2) {
        int width = Math.min(img1.getWidth(), img2.getWidth());
        int height = Math.min(img1.getHeight(), img2.getHeight());

        long diff = 0;
        long totalPixels = (long) width * height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y) & 0xFF;
                int rgb2 = img2.getRGB(x, y) & 0xFF;
                diff += Math.abs(rgb1 - rgb2);
            }
        }

        double avgDiff = diff / (double) totalPixels;
        double similarity = 100 - (avgDiff / 255.0 * 100);
        return similarity;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage sign1 = ImageIO.read(new File("signature_original.png"));
        BufferedImage sign2 = ImageIO.read(new File("signature_test.png"));

        double similarity = compareSignatures(sign1, sign2);
        System.out.println("🔎 Signature Similarity: " + similarity + "%");

        if (similarity > 80) {
            System.out.println("✅ Likely Genuine Signature.");
        } else {
            System.out.println("⚠️ Possible Forgery Detected.");
        }
    }
}
