import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageSteganography {

    public static void hideMessage(BufferedImage img, String message, String outputFile) throws Exception {
        message += "#END#"; // end marker
        int msgIndex = 0, bitIndex = 0;
        int width = img.getWidth(), height = img.getHeight();

        outer:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                if (msgIndex < message.length()) {
                    char c = message.charAt(msgIndex);
                    int bit = (c >> (7 - bitIndex)) & 1;
                    b = (b & 0xFE) | bit; // store in blue channel LSB
                    bitIndex++;
                    if (bitIndex == 8) {
                        bitIndex = 0;
                        msgIndex++;
                    }
                }

                int newRgb = (r << 16) | (g << 8) | b;
                img.setRGB(x, y, newRgb);

                if (msgIndex == message.length()) break outer;
            }
        }
        ImageIO.write(img, "png", new File(outputFile));
        System.out.println("✅ Message hidden inside " + outputFile);
    }

    public static String extractMessage(BufferedImage img) {
        StringBuilder sb = new StringBuilder();
        int width = img.getWidth(), height = img.getHeight();
        int bitIndex = 0;
        char currentChar = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int b = rgb & 0xFF;

                int bit = b & 1;
                currentChar = (char) ((currentChar << 1) | bit);
                bitIndex++;

                if (bitIndex == 8) {
                    sb.append(currentChar);
                    if (sb.toString().endsWith("#END#")) {
                        return sb.substring(0, sb.length() - 5);
                    }
                    bitIndex = 0;
                    currentChar = 0;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));

        // Hide message
        hideMessage(img, "Batman is real 😎", "hidden.png");

        // Extract message
        BufferedImage hiddenImg = ImageIO.read(new File("hidden.png"));
        String msg = extractMessage(hiddenImg);
        System.out.println("🔍 Extracted Message: " + msg);
    }
}
