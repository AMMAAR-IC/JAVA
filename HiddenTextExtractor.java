import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class HiddenTextExtractor {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("hidden.png"));
        StringBuilder sb = new StringBuilder();
        int bitIndex = 0, currentChar = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int bit = rgb & 1;
                currentChar = (currentChar << 1) | bit;
                bitIndex++;

                if (bitIndex == 8) {
                    if (currentChar == 0) {
                        System.out.println("Hidden text: " + sb);
                        return;
                    }
                    sb.append((char) currentChar);
                    bitIndex = 0;
                    currentChar = 0;
                }
            }
        }
    }
}
