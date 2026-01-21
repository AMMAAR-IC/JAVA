import java.io.File;

public class DirSize {
    static long size(File f) {
        if (f.isFile()) return f.length();
        long s = 0;
        for (File c : f.listFiles())
            s += size(c);
        return s;
    }

    public static void main(String[] args) {
        System.out.println(size(new File(".")) / 1024 + " KB");
    }
}
