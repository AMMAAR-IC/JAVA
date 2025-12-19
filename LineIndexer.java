import java.io.*;
import java.util.*;

public class LineIndexer {
    public static void main(String[] args) throws Exception {
        RandomAccessFile f = new RandomAccessFile("data.txt", "r");
        List<Long> idx = new ArrayList<>();

        idx.add(0L);
        while (f.readLine() != null)
            idx.add(f.getFilePointer());

        f.seek(idx.get(3));
        System.out.println(f.readLine());
    }
}
