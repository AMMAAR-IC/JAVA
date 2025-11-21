// ChunkHash.java
import java.io.*;
import java.security.*;

public class ChunkHash {
    public static void main(String[] args) throws Exception {
        File f = new File("bigfile.iso");
        try (InputStream in = new FileInputStream(f)) {
            byte[] buf = new byte[1024 * 1024];
            int n, chunk
