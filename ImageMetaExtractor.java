import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.Directory;
import java.io.File;

public class ImageMetaExtractor {
    public static void main(String[] args) throws Exception {
        File file = new File("photo.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory dir : metadata.getDirectories()) {
            for (Tag tag : dir.getTags()) {
                System.out.println(tag);
            }
        }
    }
}
