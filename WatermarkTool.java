import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class WatermarkTool {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.jpg"));
        Graphics2D g = img.createGraphics();
        g.setColor(new Color(255,0,0,128));
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("AMMAAR-IC", 20, img.getHeight() - 50);
        g.dispose();
        ImageIO.write(img, "png", new File("watermarked.png"));
        System.out.println("Done.");
    }
}
