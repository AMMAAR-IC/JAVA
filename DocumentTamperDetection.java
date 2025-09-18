import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class DocumentTamperDetection {

    public static void main(String[] args) throws Exception {
        BufferedImage doc = ImageIO.read(new File("document.png"));

        int width = doc.getWidth();
        int height = doc.getHeight();
        long totalDiff = 0;
        int regions = 0;

        // Scan in 20x20 blocks
        int blockSize = 20;
        for (int y = 0; y < height; y += blockSize) {
            for (int x = 0; x < width; x += blockSize) {
                long blockSum = 0;
                int count = 0;

                for (int dy = 0; dy < blockSize && y + dy < height; dy++) {
                    for (int dx = 0; dx < blockSize && x + dx < width; dx++) {
                        int rgb = doc.getRGB(x + dx, y + dy) & 0xFF;
                        blockSum += rgb;
                        count++;
                    }
                }

                long avg = blockSum / count;
                totalDiff += Math.abs(avg - 128); // mid-gray baseline
                regions++;
            }
        }

        double avgVariance = totalDiff / (double) regions;
        System.out.println("📊 Document Variance Score: " + avgVariance);

        if (avgVariance > 40) {
            System.out.println("⚠️ Possible Tampering Detected in Text Regions.");
        } else {
            System.out.println("✅ Document Appears Authentic.");
        }
    }
}
