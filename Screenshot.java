import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Screenshot {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        String fileName = "screenshot.png";
        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(screen);
        ImageIO.write(image, "png", new File(fileName));
        System.out.println("Screenshot saved as " + fileName);
    }
}
