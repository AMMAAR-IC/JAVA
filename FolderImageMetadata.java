import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FolderImageMetadata {
    public static void main(String[] args) {
        File folder = new File("images"); // replace with your folder name

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("❌ Folder not found");
            return;
        }

        File[] files = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".jpeg") ||
                name.toLowerCase().endsWith(".png"));

        if (files == null || files.length == 0) {
            System.out.println("No images found.");
            return;
        }

        for (File imageFile : files) {
            try {
                BufferedImage img = ImageIO.read(imageFile);
                if (img != null) {
                    System.out.println("📸 Image: " + imageFile.getName());
                    System.out.println("   Width: " + img.getWidth() + " px");
                    System.out.println("   Height: " + img.getHeight() + " px");
                    System.out.println("   Format: " + getFormat(imageFile.getName()));
                    System.out.println("-------------------------");
                }
            } catch (Exception e) {
                System.out.println("Error reading " + imageFile.getName());
            }
        }
    }

    private static String getFormat(String name) {
        if (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg")) return "JPEG";
        if (name.toLowerCase().endsWith(".png")) return "PNG";
        return "Unknown";
    }
}
