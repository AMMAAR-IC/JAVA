// BrotliTool.java
import java.io.*;
import java.util.zip.*;

public class BrotliTool {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("input.txt");
        BrotliOutputStream out = new BrotliOutputStream(new FileOutputStream("out.br"));
        in.transferTo(out);
        out.close();

        System.out.println("Compressed.");

        BrotliInputStream br = new BrotliInputStream(new FileInputStream("out.br"));
        br.transferTo(new FileOutputStream("dec.txt"));
        System.out.println("Decompressed.");
    }
}
