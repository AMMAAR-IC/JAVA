import java.io.File;

public class DirSizeCalculator {
    public static long getFolderSize(File folder) {
        long size = 0;
        for (File file : folder.listFiles()) {
            if (file.isFile())
                size += file.length();
            else
                size += getFolderSize(file);
        }
        return size;
    }

    public static void main(String[] args) {
        File folder = new File("C:/Users");
        System.out.println("Folder Size: " + getFolderSize(folder) / (1024 * 1024) + " MB");
    }
}
