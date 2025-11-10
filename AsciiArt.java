import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class AsciiArt {
    public static void main(String[] args) throws Exception {
        String chars = "@#%*+=-:. ";
        BufferedImage img = ImageIO.read(new File("input.jpg"));
        int w = img.getWidth(), h = img.getHeight();
        for (int y=0;y<h;y+=5) {
            StringBuilder sb = new StringBuilder();
            for (int x=0;x<w;x+=3) {
                int rgb = img.getRGB(x,y);
                int gray = ((rgb>>16)&255 + (rgb>>8)&255 + (rgb&255)) / 3;
                sb.append(chars.charAt(gray * chars.length() / 256));
            }
            System.out.println(sb);
        }
    }
}
