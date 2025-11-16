import java.io.*;
import java.util.zip.*;

public class ZipFolder {
    public static void zip(File src, ZipOutputStream out, String path) throws IOException {
        if (src.isDirectory()) {
            for (File f : src.listFiles())
                zip(f, out, path + src.getName() + "/");
        } else {
            out.putNextEntry(new ZipEntry(path + src.getName()));
            try (FileInputStream in = new FileInputStream(src)) { in.transferTo(out); }
        }
    }
    public static void main(String[] args) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("folder.zip"));
        zip(new File("myfolder"), out, "");
        out.close();
        System.out.println("ZIP created.");
    }
}
