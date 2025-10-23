import java.io.*;
import java.security.*;

public class FileHasher {
    public static void main(String[] args) throws Exception {
        File file = new File("file.txt");
        System.out.println("MD5: " + hash(file, "MD5"));
        System.out.println("SHA-256: " + hash(file, "SHA-256"));
    }
    private static String hash(File f, String algo) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algo);
        try (InputStream in = new FileInputStream(f)) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) != -1) md.update(buf, 0, n);
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
