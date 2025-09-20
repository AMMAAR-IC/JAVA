import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.FileWriter;

public class MetadataReport {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("report.html")) {
            writer.write("<html><body><h1>Image Metadata Report</h1>");

            File folder = new File("images");
            for (File file : folder.listFiles()) {
                if (file.getName().toLowerCase().endsWith(".jpg")) {
                    writer.write("<h2>" + file.getName() + "</h2><ul>");
                    Metadata metadata = ImageMetadataReader.readMetadata(file);
                    for (Directory dir : metadata.getDirectories()) {
                        for (Tag tag : dir.getTags()) {
                            writer.write("<li>" + tag + "</li>");
                        }
                    }
                    writer.write("</ul>");
                }
            }

            writer.write("</body></html>");
            System.out.println("✅ Metadata report generated → report.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
