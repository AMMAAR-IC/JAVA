import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageSteganography {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));
        String secret = "This is a hidden message!";
        
        int charIndex = 0;
        outer: for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (charIndex >= secret.length()) break outer;
                int rgb = img.getRGB(x, y);
                int newRgb = (rgb & 0xFFFFFFFE) | (secret.charAt(charIndex) & 1);
                img.setRGB(x, y, newRgb);
                charIndex++;
            }
        }
        
        ImageIO.write(img, "png", new File("output.png"));
        System.out.println("✅ Secret message hidden in output.png");
    }
}
