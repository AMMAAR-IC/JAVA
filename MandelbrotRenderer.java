// MandelbrotRenderer.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class MandelbrotRenderer {
    public static void main(String[] args) throws Exception {
        int W=1200,H=800, max=500;
        BufferedImage img = new BufferedImage(W,H,BufferedImage.TYPE_INT_RGB);
        for (int y=0;y<H;y++) for (int x=0;x<W;x++){
            double zx=0, zy=0;
            double cx = (x - W/2.0) * 4.0 / W;
            double cy = (y - H/2.0) * 4.0 / W;
            int iter=0;
            while (zx*zx + zy*zy < 4 && iter<max){
                double nx = zx*zx - zy*zy + cx;
                zy = 2*zx*zy + cy;
                zx = nx; iter++;
            }
            int col = iter==max ? 0x000000 : java.awt.Color.HSBtoRGB(iter/256f, 1, iter>0?1:0);
            img.setRGB(x,y,col);
        }
        ImageIO.write(img,"png",new File("mandelbrot.png"));
        System.out.println("Saved mandelbrot.png");
    }
}
