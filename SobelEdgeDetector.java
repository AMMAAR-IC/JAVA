import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SobelEdgeDetector {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.jpg"));
        int w=img.getWidth(), h=img.getHeight();
        BufferedImage gray = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);
        for (int y=0;y<h;y++) for (int x=0;x<w;x++) {
            int p = img.getRGB(x,y);
            int r=(p>>16)&0xff, g=(p>>8)&0xff, b=p&0xff;
            int l=(r+g+b)/3;
            int rgb = (l<<16)|(l<<8)|l;
            gray.setRGB(x,y,rgb);
        }
        BufferedImage out = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);
        int[][] gx = {{-1,0,1},{-2,0,2},{-1,0,1}};
        int[][] gy = {{1,2,1},{0,0,0},{-1,-2,-1}};
        for (int y=1;y<h-1;y++) for (int x=1;x<w-1;x++) {
            int sx=0, sy=0;
            for (int j=-1;j<=1;j++) for (int i=-1;i<=1;i++) {
                int val = (gray.getRGB(x+i,y+j)&0xff);
                sx += gx[j+1][i+1]*val;
                sy += gy[j+1][i+1]*val;
            }
            int mag = Math.min(255, (int)Math.sqrt(sx*sx + sy*sy));
            int col = (mag<<16)|(mag<<8)|mag;
            out.setRGB(x,y,col);
        }
        ImageIO.write(out,"png",new File("edges.png"));
        System.out.println("Saved edges.png");
    }
}
