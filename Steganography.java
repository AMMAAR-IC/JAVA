import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Steganography {

    public static void hideMessage(BufferedImage img, String msg) {
        msg += '\0'; // null terminator
        int msgIndex = 0, bitIndex = 0;

        outer:
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int b = rgb & 0xFF;

                if (msgIndex < msg.length()) {
                    int bit = (msg.charAt(msgIndex) >> bitIndex) & 1;
                    b = (b & 0xFE) | bit;
                    rgb = (rgb & 0xFFFFFF00) | b;
                    img.setRGB(x, y, rgb);

                    bitIndex++;
                    if (bitIndex == 8) {
                        bitIndex = 0;
                        msgIndex++;
                    }
                } else break outer;
            }
        }
    }

    public static String extractMessage(BufferedImage img) {
        StringBuilder msg = new StringBuilder();
        int currentChar = 0, bitIndex = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int b = rgb & 0xFF;
                int bit = b & 1;
                currentChar |= (bit << bitIndex);

                bitIndex++;
                if (bitIndex == 8) {
                    if (currentChar == 0) return msg.toString();
                    msg.append((char) currentChar);
                    bitIndex = 0;
                    currentChar = 0;
                }
            }
        }
        return msg.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));

        hideMessage(img, "Hello Ammaar, secret inside!");
        ImageIO.write(img, "png", new File("stego.png"));
        System.out.println("✅ Message hidden in stego.png");

        BufferedImage stego = ImageIO.read(new File("stego.png"));
        String msg = extractMessage(stego);
        System.out.println("🔎 Extracted: " + msg);
    }
}
