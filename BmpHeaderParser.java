// BmpHeaderParser.java
import java.io.*;

public class BmpHeaderParser {
    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("image.bmp", "r");

        raf.seek(18);
        int width  = Integer.reverseBytes(raf.readInt());
        int height = Integer.reverseBytes(raf.readInt());

        raf.seek(28);
        short bitsPerPixel = Short.reverseBytes(raf.readShort());

        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Bits Per Pixel: " + bitsPerPixel);
    }
}
