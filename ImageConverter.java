import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageConverter {
    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("input.png"));
            ImageIO.write(img, "jpg", new File("output.jpg"));
            System.out.println("Converted to output.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
