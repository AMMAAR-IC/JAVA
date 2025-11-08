import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DuplicateImageFinder {
    public static void main(String[] args) throws Exception {
        Path dir = Path.of("images");
        Map<String, Path> seen = new HashMap<>();
        Files.list(dir).forEach(f -> {
            try {
                BufferedImage img = ImageIO.read(f.toFile());
                String hash = hash(img);
                if (seen.containsKey(hash))
                    System.out.println("Duplicate found: " + f.getFileName() + " = " + seen.get(hash).getFileName());
                else seen.put(hash, f);
            } catch (Exception ignored) {}
        });
    }
    static String hash(BufferedImage img) {
        int avg = 0, count = 0;
        for (int y=0;y<img.getHeight();y++)
            for (int x=0;x<img.getWidth();x++) {
                avg += img.getRGB(x,y) & 0xFFFFFF;
                count++;
            }
        return Integer.toHexString(avg / count);
    }
}
