import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BlurDetection {

    // Convert image to grayscale
    private static double[][] toGrayscale(BufferedImage img) {
        int w = img.getWidth(), h = img.getHeight();
        double[][] gray = new double[h][w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                gray[y][x] = (r + g + b) / 3.0;
            }
        }
        return gray;
    }

    // Apply Laplacian operator
    private static double[][] laplacian(double[][] gray) {
        int h = gray.length, w = gray[0].length;
        double[][] lap = new double[h][w];
        int[][] kernel = {{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};

        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                double sum = 0;
                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        sum += gray[y + ky][x + kx] * kernel[ky + 1][kx + 1];
                    }
                }
                lap[y][x] = sum;
            }
        }
        return lap;
    }

    // Compute variance of Laplacian
    private static double variance(double[][] lap) {
        double sum = 0, sumSq = 0;
        int count = 0;
        for (double[] row : lap) {
            for (double val : row) {
                sum += val;
                sumSq += val * val;
                count++;
            }
        }
        double mean = sum / count;
        return (sumSq / count) - (mean * mean);
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));

        double[][] gray = toGrayscale(img);
        double[][] lap = laplacian(gray);
        double var = variance(lap);

        System.out.println("📷 Blur Score (Variance of Laplacian): " + var);
        if (var < 100) { // threshold (tune as needed)
            System.out.println("❌ Image is likely BLURRY");
        } else {
            System.out.println("✅ Image is SHARP");
        }
    }
}
