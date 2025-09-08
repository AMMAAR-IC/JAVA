import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SobelEdgeDetection {

    public static BufferedImage applySobel(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage edgeImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        int[][] gx = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] gy = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sumX = 0, sumY = 0;
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int rgb = img.getRGB(x + i, y + j);
                        int gray = (rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff);
                        gray /= 3;
                        sumX += gx[j + 1][i + 1] * gray;
                        sumY += gy[j + 1][i + 1] * gray;
                    }
                }
                int magnitude = (int) Math.min(255, Math.sqrt(sumX * sumX + sumY * sumY));
                int newPixel = (magnitude << 16) | (magnitude << 8) | magnitude;
                edgeImg.setRGB(x, y, newPixel);
            }
        }
        return edgeImg;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage input = ImageIO.read(new File("input.png"));
        BufferedImage edges = applySobel(input);
        ImageIO.write(edges, "png", new File("edges.png"));
        System.out.println("✅ Edge-detected image saved -> edges.png");
    }
}
