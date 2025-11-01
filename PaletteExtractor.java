// PaletteExtractor.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class PaletteExtractor {
    public static Map<Integer,Integer> palette(BufferedImage img) {
        Map<Integer,Integer> counts = new HashMap<>();
        for (int y=0;y<img.getHeight();y++) for (int x=0;x<img.getWidth();x++) {
            int rgb = img.getRGB(x,y) & 0xFFFFFF;
            counts.put(rgb, counts.getOrDefault(rgb,0)+1);
        }
        return counts;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.jpg"));
        var map = palette(img);
        map.entrySet().stream().sorted((a,b)->b.getValue()-a.getValue()).limit(8)
            .forEach(e -> System.out.printf("#%06X -> %d\n", e.getKey(), e.getValue()));
    }
}
