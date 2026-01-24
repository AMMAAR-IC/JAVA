
import java.io.*;
import java.nio.channels.*;

public class FileLocker {
    public static void main(String[] args) throws Exception {
        FileChannel ch = new RandomAccessFile("lock.txt","rw").getChannel();
        FileLock lock = ch.lock();
        System.out.println("Locked. Press enter to release.");
        System.in.read();
        lock.release();
        ch.close();
    }
}
