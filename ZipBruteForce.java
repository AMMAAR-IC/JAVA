// ZipBruteForce.java
import java.util.zip.*;
import java.io.*;

public class ZipBruteForce {
    static char[] chars = "abc123XYZ".toCharArray();

    public static void main(String[] args) throws Exception {
        brute("", 4);
    }

    static void brute(String prefix, int max) {
        if (prefix.length() == max) return;

        for (char c : chars) {
            String pass = prefix + c;
            try {
                ZipFile z = new ZipFile("test.zip", pass.toCharArray());
                System.out.println("FOUND PASSWORD: " + pass);
                System.exit(0);
            } catch (Exception ignored) {}
            brute(pass, max);
        }
    }
}
