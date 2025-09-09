import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageDuplicateDetector {

    public static String averageHash(BufferedImage img) {
        int size = 8;
        BufferedImage scaled = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
        scaled.getGraphics().drawImage(img, 0, 0, size, size, null);

        long total = 0;
        int[] pixels = new int[size * size];
        scaled.getRaster().getPixels(0, 0, size, size, pixels);
        for (int val : pixels) total += val;
        double avg = total / (double)(size * size);

        StringBuilder hash = new StringBuilder();
        for (int val : pixels) hash.append(val > avg ? "1" : "0");
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
        BufferedImage img1 = ImageIO.read(new File("img1.png"));
        BufferedImage img2 = ImageIO.read(new File("img2.png"));

        String h1 = averageHash(img1);
        String h2 = averageHash(img2);

        int dist = hammingDistance(h1, h2);
        System.out.println("🔑 Hamming Distance: " + dist);
        if (dist < 5) System.out.println("✅ Images are likely duplicates");
        else System.out.println("❌ Images are different");
    }
}
