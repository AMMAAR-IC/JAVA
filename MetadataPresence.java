import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;

import java.io.File;

public class MetadataPresence {
    public static void main(String[] args) {
        try {
            File image = new File("test.jpg");
            Metadata metadata = ImageMetadataReader.readMetadata(image);

            if (metadata.getDirectories().isEmpty()) {
                System.out.println("⚠️ No metadata → Probably edited/exported.");
            } else {
                System.out.println("✅ Metadata present.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
