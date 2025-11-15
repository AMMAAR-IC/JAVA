import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.*;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;

public class EXIFToMaps {
    public static void main(String[] args) throws Exception {
        File img = new File("photo.jpg");
        Metadata md = ImageMetadataReader.readMetadata(img);
        GpsDirectory gps = md.getFirstDirectoryOfType(GpsDirectory.class);

        if (gps != null) {
            var loc = gps.getGeoLocation();
            System.out.println("Lat: " + loc.getLatitude());
            System.out.println("Lon: " + loc.getLongitude());
            System.out.println("Maps URL:");
            System.out.println("https://www.google.com/maps?q=" +
                    loc.getLatitude() + "," + loc.getLongitude());
        } else System.out.println("No GPS found.");
    }
}
