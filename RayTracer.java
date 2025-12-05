import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class RayTracer {
    public static void main(String[] args) throws Exception {
        int W=800,H=600;
        BufferedImage img = new BufferedImage(W,H,BufferedImage.TYPE_INT_RGB);

        for (int y=0;y<H;y++) for (int x=0;x<W;x++) {
            double nx = (x - W/2.0) / W;
            double ny = (y - H/2.0) / H;
            double nz = 1;

            double cx=0, cy=0, cz=3; // sphere center
            double r=1;

            double A = nx*nx + ny*ny + nz*nz;
            double B = 2*(nx*cx + ny*cy + nz*cz);
            double C = cx*cx + cy*cy + cz*cz - r*r;

            double disc = B*B - 4*A*C;
            if (disc >= 0) {
                double t = (-B - Math.sqrt(disc))/(2*A);
                double lx = nx*t - cx;
                double ly = ny*t - cy;
                double lz = nz*t - cz;
                double shade = Math.abs(lz)/Math.sqrt(lx*lx + ly*ly + lz*lz);
                int col = (int)(shade*255);
                img.setRGB(x,y,(col<<16)|(col<<8)|col);
            }
        }

        ImageIO.write(img,"png",new File("raytrace.png"));
        System.out.println("Saved raytrace.png");
    }
}
