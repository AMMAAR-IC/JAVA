// MedianFilter.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class MedianFilter {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("noisy.png"));
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        for (int y=1; y<img.getHeight()-1; y++)
            for (int x=1; x<img.getWidth()-1; x++) {
                List<Integer> vals = new ArrayList<>();
                for (int dy=-1; dy<=1; dy++)
                    for (int dx=-1; dx<=1; dx++)
                        vals.add(img.getRGB(x+dx,y+dy));
                Collections.sort(vals);
                out.setRGB(x,y, vals.get(4));
            }

        ImageIO.write(out,"png",new File("denoised.png"));
    }
}
