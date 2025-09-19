import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

import java.io.File;
import java.io.FileWriter;

public class GPSBatchKML {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("locations.kml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document>\n");

            File folder = new File("images");
            for (File file : folder.listFiles()) {
                if (file.getName().toLowerCase().endsWith(".jpg")) {
                    Metadata metadata = ImageMetadataReader.readMetadata(file);
                    GpsDirectory gps = metadata.getFirstDirectoryOfType(GpsDirectory.class);
                    if (gps != null && gps.getGeoLocation() != null) {
                        double lat = gps.getGeoLocation().getLatitude();
                        double lon = gps.getGeoLocation().getLongitude();
                        writer.write("<Placemark><name>" + file.getName() + "</name><Point><coordinates>"
                                + lon + "," + lat + "</coordinates></Point></Placemark>\n");
                    }
                }
            }

            writer.write("</Document>\n</kml>");
            System.out.println("✅ KML file created: locations.kml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
