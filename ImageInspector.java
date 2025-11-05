import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Formatter;

public class ImageInspector {

    public static void main(String[] args) {
        File folder = new File("images");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().matches(".*\\.(jpg|jpeg|png|bmp|tiff)$"));

        if (files == null || files.length == 0) {
            System.out.println("No images found in 'images' folder.");
            return;
        }

        for (File image : files) {
            System.out.println("📷 File: " + image.getName());
            System.out.println("──────────────────────────────");

            // Extract metadata
            try {
                Metadata metadata = ImageMetadataReader.readMetadata(image);
                for (Directory dir : metadata.getDirectories()) {
                    for (Tag tag : dir.getTags()) {
                        System.out.printf("[%s] %s = %s%n", dir.getName(), tag.getTagName(), tag.getDescription());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading metadata: " + e.getMessage());
            }

            // Compute hashes
            try {
                System.out.println("\n🔒 MD5: " + hashFile(image, "MD5"));
                System.out.println("🔐 SHA-256: " + hashFile(image, "SHA-256"));
            } catch (Exception e) {
                System.out.println("Error computing hash: " + e.getMessage());
            }

            System.out.println("\n=====================================\n");
        }
    }

    private static String hashFile(File file, String algorithm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int n;
            while ((n = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, n);
            }
        }
        return byteToHex(digest.digest());
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
