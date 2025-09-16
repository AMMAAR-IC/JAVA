import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageHashing {

    public static String getAverageHash(BufferedImage img) {
        // Resize to 8x8 grayscale
        BufferedImage resized = new BufferedImage(8, 8, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(img, 0, 0, 8, 8, null);
        g2d.dispose();

        int[] pixels = new int[64];
        int index = 0, sum = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int gray = resized.getRGB(x, y) & 0xFF;
                pixels[index++] = gray;
                sum += gray;
            }
        }

        int avg = sum / 64;
        StringBuilder hash = new StringBuilder();
        for (int p : pixels) {
            hash.append(p >= avg ? "1" : "0");
        }
        return hash.toString();
    }

    public static int hammingDistance(String h1, String h2) {
        int dist = 0;
        for (int i = 0; i < h1.length(); i++) {
            if (h1.charAt(i) != h2.charAt(i)) dist++;
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img1 = ImageIO.read(new File("img1.jpg"));
        BufferedImage img2 = ImageIO.read(new File("img2.jpg"));

        String hash1 = getAverageHash(img1);
        String hash2 = getAverageHash(img2);

        int distance = hammingDistance(hash1, hash2);
        System.out.println("Image 1 Hash: " + hash1);
        System.out.println("Image 2 Hash: " + hash2);
        System.out.println("Hamming Distance = " + distance);

        if (distance <= 5)
            System.out.println("✅ Images are very similar!");
        else
            System.out.println("❌ Images are different.");
    }
}
