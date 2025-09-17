import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class DeepfakeDetector {

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("suspect_face.jpg"));

        int width = img.getWidth();
        int height = img.getHeight();

        double totalVariance = 0;
        int pixelCount = 0;

        // Analyze edges for abnormal pixel variance
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int rgb = img.getRGB(x, y);
                int rgbRight = img.getRGB(x + 1, y);
                int rgbDown = img.getRGB(x, y + 1);

                int diffRight = Math.abs((rgb & 0xFFFFFF) - (rgbRight & 0xFFFFFF));
                int diffDown = Math.abs((rgb & 0xFFFFFF) - (rgbDown & 0xFFFFFF));

                totalVariance += (diffRight + diffDown) / 2.0;
                pixelCount++;
            }
        }

        double avgVariance = totalVariance / pixelCount;
        System.out.println("📊 Edge Variance Score: " + avgVariance);

        if (avgVariance > 15000) {
            System.out.println("⚠️ Possible Manipulation Detected (Deepfake/Face Swap Suspected).");
        } else {
            System.out.println("✅ Image appears authentic.");
        }
    }
}
