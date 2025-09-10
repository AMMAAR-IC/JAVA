import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageColorization {

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("gray.png"));
        int w = img.getWidth(), h = img.getHeight();

        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int gray = img.getRGB(x, y) & 0xFF;
                int r = gray;
                int g = 255 - gray;
                int b = (gray * 2) % 255;
                int color = (r << 16) | (g << 8) | b;
                output.setRGB(x, y, color);
            }
        }

        ImageIO.write(output, "png", new File("colorized.png"));
        System.out.println("✅ Colorized image saved as colorized.png");
    }
}
