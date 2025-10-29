import java.io.*;
import java.util.*;

public class FileSignatureDetector {
    public static void main(String[] args) throws Exception {
        File f = new File("test.jpg");
        byte[] sig = new byte[8];
        try (FileInputStream fis = new FileInputStream(f)) { fis.read(sig); }
        String hex = bytesToHex(sig);
        Map<String,String> map = Map.of(
            "FFD8FF", "JPEG Image",
            "89504E47", "PNG Image",
            "47494638", "GIF Image",
            "25504446", "PDF Document"
        );
        System.out.println("File: " + f.getName());
        map.forEach((k,v)->{ if(hex.startsWith(k)) System.out.println("Detected: " + v); });
        System.out.println("Hex: " + hex);
    }

    static String bytesToHex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for(byte x: b) sb.append(String.format("%02X", x));
        return sb.toString();
    }
}
