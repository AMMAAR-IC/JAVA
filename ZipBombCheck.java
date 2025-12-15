import java.util.zip.*;
import java.io.*;

public class ZipBombCheck {
    public static void main(String[] args) throws Exception {
        ZipFile z = new ZipFile("file.zip");
        z.stream().forEach(e -> {
            long ratio = e.getCompressedSize() == 0 ? 0 :
                    e.getSize() / e.getCompressedSize();
            if (ratio > 100)
                System.out.println("Suspicious: " + e.getName());
        });
    }
}
