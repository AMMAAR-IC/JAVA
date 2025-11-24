// LSBDecoder.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class LSBDecoder {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("stego.png"));
        StringBuilder sb = new StringBuilder();
        int bit = 0, cur = 0;

        outer:
        for (int y=0;y<img.getHeight();y++)
            for (int x=0;x<img.getWidth();x++) {
                int b = img.getRGB(x,y) & 1;
                cur = (cur << 1) | b;
                bit++;
                if (bit == 8) {
                    if (cur == 0) break outer;
                    sb.append((char)cur);
                    bit = 0; cur = 0;
                }
            }

        System.out.println("Hidden Message: " + sb);
    }
}
