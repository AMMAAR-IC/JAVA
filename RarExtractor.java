// RarExtractor.java
import java.io.*;

public class RarExtractor {
    public static void main(String[] args) throws Exception {
        String file = "archive.rar";
        Process p = new ProcessBuilder("unrar", "x", file).start();
        p.waitFor();
        System.out.println("Extracted: " + file);
    }
}
