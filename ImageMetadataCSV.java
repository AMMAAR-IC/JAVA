import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageMetadataCSV {
    public static void main(String[] args) {
        File folder = new File("images");
        File csvFile = new File("image_metadata.csv");

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("FileName,Width,Height,Format");

            File[] files = folder.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".jpg") ||
                    name.toLowerCase().endsWith(".jpeg") ||
                    name.toLowerCase().endsWith(".png"));

            if (files == null) {
                System.out.println("No images found.");
                return;
            }

            for (File imageFile : files) {
                try {
                    BufferedImage img = ImageIO.read(imageFile);
                    if (img != null) {
                        String format = getFormat(imageFile.getName());
                        writer.printf("%s,%d,%d,%s\n",
                                imageFile.getName(),
                                img.getWidth(),
                                img.getHeight(),
                                format);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing " + imageFile.getName());
                }
            }
        } catch (IOException e) {
            System.out.println("CSV write error: " + e.getMessage());
        }

        System.out.println("✅ Metadata saved to image_metadata.csv");
    }

    private static String getFormat(String name) {
        if (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg")) return "JPEG";
        if (name.toLowerCase().endsWith(".png")) return "PNG";
        return "Unknown";
    }
}
