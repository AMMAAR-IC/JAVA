import java.io.File;

public class FolderSize {
    public static long getSize(File f){
        if (f.isFile()) return f.length();
        long total = 0;
        File[] files = f.listFiles();
        if (files != null) for (File x : files) total += getSize(x);
        return total;
    }
    public static void main(String[] args){
        File dir = new File(".");
        System.out.printf("Total size: %.2f MB%n", getSize(dir)/1e6);
    }
}
