import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageToASCII {
    private static final String ASCII_CHARS = "@#S%?*+;:,. ";

    public static String convertToASCII(BufferedImage img, int newWidth) {
        int width = img.getWidth();
        int height = img.getHeight();
        int newHeight = (height * newWidth) / width;

        BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        resized.getGraphics().drawImage(img, 0, 0, newWidth, newHeight, null);

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int gray = resized.getRGB(x, y) & 0xFF;
                int index = (gray * (ASCII_CHARS.length() - 1)) / 255;
                sb.append(ASCII_CHARS.charAt(index));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));
        String asciiArt = convertToASCII(img, 100);
        System.out.println(asciiArt);
    }
}
