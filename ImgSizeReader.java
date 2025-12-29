import javax.imageio.*;
import java.io.*;

public class ImgSizeReader {
    public static void main(String[] args)throws Exception{
        var img=ImageIO.read(new File("pic.jpg"));
        System.out.println(img.getWidth()+"x"+img.getHeight());
    }
}
