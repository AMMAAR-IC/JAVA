// ID3v1Writer.java
import java.io.*;

public class ID3v1Writer {
    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("song.mp3", "rw");
        raf.seek(raf.length() - 128);
        raf.write("TAG".getBytes());
        raf.write(pad("AMMAAR SONG", 30));
        raf.write(pad("JAVA ARTIST", 30));
        raf.write(pad("ALBUM X", 30));
        raf.write("2025".getBytes());
        raf.write(new byte[30]); // comment
        raf.writeByte(0); // genre
        raf.close();
        System.out.println("ID3 tag written.");
    }

    static byte[] pad(String s, int len){
        return (s + " ".repeat(len-s.length())).substring(0,len).getBytes();
    }
}
