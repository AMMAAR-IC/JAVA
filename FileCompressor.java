// FileCompressor.java
import java.io.*;
import java.util.zip.*;

public class FileCompressor {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("data.txt");
        FileOutputStream fos = new FileOutputStream("data.gz");
        GZIPOutputStream gzip = new GZIPOutputStream(fos);

        fis.transferTo(gzip);
        gzip.close();
        fis.close();
        System.out.println("✅ File compressed!");
    }
}
