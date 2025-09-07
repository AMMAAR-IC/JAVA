import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.util.*;

public class ImageColorPaletteExtractor {

    static class Cluster {
        double r, g, b;
        List<Color> pixels = new ArrayList<>();

        Cluster(Color c) {
            this.r = c.getRed();
            this.g = c.getGreen();
            this.b = c.getBlue();
        }

        void addPixel(Color c) {
            pixels.add(c);
        }

        void updateCenter() {
            if (pixels.isEmpty()) return;
            double rSum = 0, gSum = 0, bSum = 0;
            for (Color c : pixels) {
                rSum += c.getRed();
                gSum += c.getGreen();
                bSum += c.getBlue();
            }
            int size = pixels.size();
            r = rSum / size;
            g = gSum / size;
            b = bSum / size;
            pixels.clear();
        }

        Color getCenterColor() {
            return new Color((int) r, (int) g, (int) b);
        }
    }

    public static List<Color> getDominantColors(BufferedImage img, int k, int iterations) {
        Random rand = new Random();
        List<Cluster> clusters = new ArrayList<>();

        // Randomly initialize clusters
        for (int i = 0; i < k; i++) {
            int x = rand.nextInt(img.getWidth());
            int y = rand.nextInt(img.getHeight());
            clusters.add(new Cluster(new Color(img.getRGB(x, y))));
        }

        for (int it = 0; it < iterations; it++) {
            // Assign pixels to nearest cluster
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color c = new Color(img.getRGB(x, y));
                    Cluster best = null;
                    double minDist = Double.MAX_VALUE;
                    for (Cluster cl : clusters) {
                        double dist = Math.pow(c.getRed() - cl.r, 2) +
                                      Math.pow(c.getGreen() - cl.g, 2) +
                                      Math.pow(c.getBlue() - cl.b, 2);
                        if (dist < minDist) {
                            minDist = dist;
                            best = cl;
                        }
                    }
                    best.addPixel(c);
                }
            }

            // Update cluster centers
            for (Cluster cl : clusters) cl.updateCenter();
        }

        List<Color> colors = new ArrayList<>();
        for (Cluster cl : clusters) colors.add(cl.getCenterColor());
        return colors;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("test.jpg"); // put an image in project folder
        BufferedImage img = ImageIO.read(file);

        List<Color> palette = getDominantColors(img, 5, 5);
        System.out.println("🎨 Dominant Colors:");
        for (Color c : palette) {
            System.out.printf("RGB(%d, %d, %d)\n", c.getRed(), c.getGreen(), c.getBlue());
        }
    }
}            b = bSum / size;
            pixels.clear();
        }

        Color getCenterColor() {
            return new Color((int) r, (int) g, (int) b);
        }
    }

    public static List<Color> getDominantColors(BufferedImage img, int k, int iterations) {
        Random rand = new Random();
        List<Cluster> clusters = new ArrayList<>();

        // Randomly initialize clusters
        for (int i = 0; i < k; i++) {
            int x = rand.nextInt(img.getWidth());
            int y = rand.nextInt(img.getHeight());
            clusters.add(new Cluster(new Color(img.getRGB(x, y))));
        }

        for (int it = 0; it < iterations; it++) {
            // Assign pixels to nearest cluster
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color c = new Color(img.getRGB(x, y));
                    Cluster best = null;
                    double minDist = Double.MAX_VALUE;
                    for (Cluster cl : clusters) {
                        double dist = Math.pow(c.getRed() - cl.r, 2) +
                                      Math.pow(c.getGreen() - cl.g, 2) +
                                      Math.pow(c.getBlue() - cl.b, 2);
                        if (dist < minDist) {
                            minDist = dist;
                            best = cl;
                        }
                    }
                    best.addPixel(c);
                }
            }

            // Update cluster centers
            for (Cluster cl : clusters) cl.updateCenter();
        }

        List<Color> colors = new ArrayList<>();
        for (Cluster cl : clusters) colors.add(cl.getCenterColor());
        return colors;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("test.jpg"); // put an image in project folder
        BufferedImage img = ImageIO.read(file);

        List<Color> palette = getDominantColors(img, 5, 5);
        System.out.println("🎨 Dominant Colors:");
        for (Color c : palette) {
            System.out.printf("RGB(%d, %d, %d)\n", c.getRed(), c.getGreen(), c.getBlue());
        }
    }
}
