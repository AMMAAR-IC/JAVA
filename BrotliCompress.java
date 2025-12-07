import java.io.*;

public class BrotliCompress {
    public static void main(String[] args) throws Exception {
        Process p = new ProcessBuilder("brotli", "-f", "input.txt").start();
        p.waitFor();
        System.out.println("Compressed -> input.txt.br");
    }
}
