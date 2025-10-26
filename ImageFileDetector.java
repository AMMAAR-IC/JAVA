import java.io.File;

public class ImageFileDetector {
    public static void main(String[] args) {
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg"));
        if (files != null) {
            for (File f : files) {
                System.out.println("Found image: " + f.getName());
            }
        }
    }
}
