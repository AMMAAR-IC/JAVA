import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

import java.io.File;

public class GPSLink {
    public static void main(String[] args) {
        try {
            File image = new File("geo.jpg");
            Metadata metadata = ImageMetadataReader.readMetadata(image);

            GpsDirectory gps = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (gps != null && gps.getGeoLocation() != null) {
                double lat = gps.getGeoLocation().getLatitude();
                double lon = gps.getGeoLocation().getLongitude();

                String link = "https://maps.google.com/?q=" + lat + "," + lon;
                System.out.println("🌍 Location: " + link);
            } else {
                System.out.println("No GPS data found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
