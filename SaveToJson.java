import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SaveToJson {
    public static void main(String[] args) {
        try {
            File image = new File("test.jpg");
            Metadata metadata = ImageMetadataReader.readMetadata(image);

            Map<String, String> data = new HashMap<>();
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    data.put(tag.getTagName(), tag.getDescription());
                }
            }

            Gson gson = new Gson();
            try (FileWriter writer = new FileWriter("metadata.json")) {
                gson.toJson(data, writer);
            }

            System.out.println("Metadata saved to metadata.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
