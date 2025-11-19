// LSBEncoder.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class LSBEncoder {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));
        String secret = "hello repo!";
        byte[] bytes = (secret + "\0").getBytes();
        int idx=0, bit=0;
        outer: for (int y=0;y<img.getHeight();y++)
            for (int x=0;x<img.getWidth();x++) {
                int rgb = img.getRGB(x,y);
                int b = rgb & 0xFF;
                int newb = (b & ~1) | ((bytes[idx] >> (7-bit)) & 1);
                int newRgb = (rgb & 0xFFFFFF00) | (newb & 0xFF);
                img.setRGB(x,y,newRgb);
                bit++; if (bit==8) { bit=0; idx++; if (idx==bytes.length) break outer; }
            }
        ImageIO.write(img, "png", new File("stego.png"));
        System.out.println("Saved stego.png");
    }
}
