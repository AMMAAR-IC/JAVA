import java.io.*;

public class FileTail {
    public static void main(String[] args) throws Exception {
        RandomAccessFile f = new RandomAccessFile("app.log", "r");
        long pos = f.length();

        while (true) {
            long len = f.length();
            if (len > pos) {
                f.seek(pos);
                System.out.print(f.readLine());
                pos = f.getFilePointer();
            }
            Thread.sleep(500);
        }
    }
}
