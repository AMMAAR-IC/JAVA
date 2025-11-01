// ExifStripper.java
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ExifStripper {
    public static void strip(String inPath, String outPath) throws Exception {
        BufferedImage img = ImageIO.read(new File(inPath));
        ImageIO.write(img, "jpg", new File(outPath)); // re-encodes without original metadata
    }
    public static void main(String[] args) throws Exception {
        strip("photo_with_exif.jpg", "photo_clean.jpg");
        System.out.println("Saved photo_clean.jpg (metadata removed)");
    }
}
