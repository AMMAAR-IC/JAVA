import java.io.*;
import java.util.zip.*;

public class CompressionRatio {
    public static void main(String[] args) throws Exception {
        File f = new File("data.txt");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gz = new GZIPOutputStream(out);
        gz.write(new FileInputStream(f).readAllBytes());
        gz.close();

        System.out.println("Ratio: " +
                (double)f.length() / out.size());
    }
}
