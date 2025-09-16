import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class ImageForgeryDetector {

    private static final int BLOCK_SIZE = 16;

    static class Block {
        int[] pixels;
        int x, y;

        Block(int[] pixels, int x, int y) {
            this.pixels = pixels;
            this.x = x;
            this.y = y;
        }
    }

    public static List<String> detectForgery(BufferedImage img) {
        List<Block> blocks = new ArrayList<>();
        int width = img.getWidth();
        int height = img.getHeight();

        for (int y = 0; y < height - BLOCK_SIZE; y += BLOCK_SIZE) {
            for (int x = 0; x < width - BLOCK_SIZE; x += BLOCK_SIZE) {
                int[] pixels = new int[BLOCK_SIZE * BLOCK_SIZE];
                int index = 0;
                for (int dy = 0; dy < BLOCK_SIZE; dy++) {
                    for (int dx = 0; dx < BLOCK_SIZE; dx++) {
                        pixels[index++] = img.getRGB(x + dx, y + dy);
                    }
                }
                blocks.add(new Block(pixels, x, y));
            }
        }

        List<String> matches = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {
            for (int j = i + 1; j < blocks.size(); j++) {
                if (Arrays.equals(blocks.get(i).pixels, blocks.get(j).pixels)) {
                    matches.add("⚠️ Possible Copy-Move Detected between (" 
                                + blocks.get(i).x + "," + blocks.get(i).y + 
                                ") and (" + blocks.get(j).x + "," + blocks.get(j).y + ")");
                }
            }
        }
        return matches;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("suspect.png"));
        List<String> report = detectForgery(img);

        if (report.isEmpty()) {
            System.out.println("✅ No forgery detected.");
        } else {
            report.forEach(System.out::println);
        }
    }
}
