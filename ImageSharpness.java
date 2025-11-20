// ImageSharpness.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageSharpness {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("photo.jpg"));
        long sum = 0, squares = 0;
        int w = img.getWidth(), h = img.getHeight();

        for (int y=1;y<h-1;y++)
            for (int x=1;x<w-1;x++) {
                int val = (img.getRGB(x,y) & 255)*4
                        - (img.getRGB(x-1,y) & 255)
                        - (img.getRGB(x+1,y) & 255)
                        - (img.getRGB(x,y-1) & 255)
                        - (img.getRGB(x,y+1) & 255);
                sum += val;
                squares += val*val;
            }

        double variance = (squares - (sum*sum)/(double)(w*h)) / (w*h);
        System.out.println("Sharpness score: " + variance);
    }
}
