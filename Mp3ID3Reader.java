// Mp3ID3Reader.java
import java.io.*;

public class Mp3ID3Reader {
    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("song.mp3", "r");
        byte[] header = new byte[10];
        raf.readFully(header);

        if (!new String(header, 0, 3).equals("ID3")) {
            System.out.println("No ID3 tags found.");
            return;
        }

        int size = ((header[6] & 0x7F) << 21)
                 | ((header[7] & 0x7F) << 14)
                 | ((header[8] & 0x7F) << 7)
                 | (header[9] & 0x7F);

        byte[] data = new byte[size];
        raf.readFully(data);

        String text = new String(data);
        if (text.contains("TIT2"))
            System.out.println("Title: " + text.split("TIT2")[1].substring(3, 20).trim());
        if (text.contains("TPE1"))
            System.out.println("Artist: " + text.split("TPE1")[1].substring(3, 20).trim());
    }
}
