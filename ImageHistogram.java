import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class ImageHistogram {

    public static void generateHistogram(BufferedImage img) {
        int[] red = new int[256];
        int[] green = new int[256];
        int[] blue = new int[256];
        int[] gray = new int[256];

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                red[r]++;
                green[g]++;
                blue[b]++;
                gray[(r + g + b) / 3]++;
            }
        }

        System.out.println("📊 Red Histogram: " + Arrays.toString(red));
        System.out.println("📊 Green Histogram: " + Arrays.toString(green));
        System.out.println("📊 Blue Histogram: " + Arrays.toString(blue));
        System.out.println("📊 Grayscale Histogram: " + Arrays.toString(gray));
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));
        generateHistogram(img);
    }
}
