import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifThumbnailDirectory;

import java.io.File;
import java.io.FileOutputStream;

public class ThumbnailExtractor {
    public static void main(String[] args) {
        try {
            File image = new File("photo.jpg");
            Metadata metadata = ImageMetadataReader.readMetadata(image);

            ExifThumbnailDirectory dir = metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class);
            if (dir != null && dir.hasThumbnailData()) {
                byte[] thumb = dir.getThumbnailData();
                FileOutputStream fos = new FileOutputStream("thumbnail.jpg");
                fos.write(thumb);
                fos.close();
                System.out.println("✅ Thumbnail extracted → thumbnail.jpg");
            } else {
                System.out.println("No thumbnail found in EXIF.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
