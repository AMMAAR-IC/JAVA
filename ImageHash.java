// ImageHash.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class ImageHash {
    public static void main(String[] args) throws Exception {
        String img1 = hash("img1.jpg");
        String img2 = hash("img2.jpg");
        System.out.println("Hash 1: " + img1);
        System.out.println("Hash 2: " + img2);
        System.out.println("Similarity: " + compare(img1, img2) + "%");
    }

    static String hash(String path) throws Exception {
        BufferedImage img = ImageIO.read(new File(path));
        img = new BufferedImage(8, 8, BufferedImage.TYPE_BYTE_GRAY);
        int avg = 0, total = 0;
        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++) avg += (img.getRGB(x, y) & 0xFF);
        avg /= 64;
        StringBuilder hash = new StringBuilder();
        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++)
                hash.append(((img.getRGB(x, y) & 0xFF) > avg) ? "1" : "0");
        return hash.toString();
    }

    static int compare(String h1, String h2) {
        int diff = 0;
        for (int i = 0; i < h1.length(); i++)
            if (h1.charAt(i) == h2.charAt(i)) diff++;
        return diff * 100 / h1.length();
    }
}
