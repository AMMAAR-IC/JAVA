import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class ColorPaletteExtractor {

    public static String toHex(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return String.format("#%02X%02X%02X", r, g, b);
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));

        Map<Integer, Integer> freq = new HashMap<>();
        int w = img.getWidth(), h = img.getHeight();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y) & 0xFFFFFF;
                freq.put(rgb, freq.getOrDefault(rgb, 0) + 1);
            }
        }

        // Sort by frequency
        List<Map.Entry<Integer, Integer>> sorted = new ArrayList<>(freq.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("🎨 Dominant Colors:");
        for (int i = 0; i < Math.min(5, sorted.size()); i++) {
            int rgb = sorted.get(i).getKey();
            String hex = toHex(rgb);
            System.out.println(hex + " (Count: " + sorted.get(i).getValue() + ")");
        }
    }
}
