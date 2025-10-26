import java.io.File;

public class HiddenFileFinder {
    public static void main(String[] args) {
        File dir = new File(".");
        for (File f : dir.listFiles()) {
            if (f.isHidden()) System.out.println("Hidden: " + f.getName());
        }
    }
}
