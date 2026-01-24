import java.io.*;

public class Fragmentation {
    public static void main(String[] args) throws Exception {
        RandomAccessFile f = new RandomAccessFile("data.bin","r");
        long size = f.length();
        System.out.println("Chunks: " + (size / 4096));
        f.close();
    }
}
