import java.io.*;

public class FileMerger {
    public static void main(String[] args) throws Exception {
        FileOutputStream out = new FileOutputStream("merged.dat");

        for (int i = 0; ; i++) {
            File f = new File("chunk_" + i);
            if (!f.exists()) break;
            out.write(new FileInputStream(f).readAllBytes());
        }
        out.close();
        System.out.println("Merged");
    }
}
