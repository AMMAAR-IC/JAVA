import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageWatermarkLSB {

    // Embed watermark text into image
    public static void embedWatermark(BufferedImage img, String watermark, String outputPath) throws Exception {
        byte[] data = watermark.getBytes();
        int dataIndex = 0, bitIndex = 0;

        outer:
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;

                if (dataIndex < data.length) {
                    int bit = (data[dataIndex] >> (7 - bitIndex)) & 1;
                    r = (r & 0xFE) | bit;
                    rgb = (rgb & 0xFF00FFFF) | (r << 16);
                    img.setRGB(x, y, rgb);

                    bitIndex++;
                    if (bitIndex == 8) {
                        bitIndex = 0;
                        dataIndex++;
                    }
                } else break outer;
            }
        }
        ImageIO.write(img, "png", new File(outputPath));
    }

    // Extract watermark text from image
    public static String extractWatermark(BufferedImage img, int length) {
        StringBuilder sb = new StringBuilder();
        int dataIndex = 0, bitIndex = 0, currentByte = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (dataIndex >= length) return sb.toString();

                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;

                currentByte = (currentByte << 1) | (r & 1);
                bitIndex++;

                if (bitIndex == 8) {
                    sb.append((char) currentByte);
                    bitIndex = 0;
                    currentByte = 0;
                    dataIndex++;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("original.png"));

        // Embed watermark
        embedWatermark(img, "HELLOJAVA", "watermarked.png");

        // Extract watermark
        BufferedImage wmImg = ImageIO.read(new File("watermarked.png"));
        String hidden = extractWatermark(wmImg, 9); // 9 characters
        System.out.println("Extracted Watermark: " + hidden);
    }
}
