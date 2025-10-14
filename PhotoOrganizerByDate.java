// PhotoOrganizerByDate.java
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.exif.*;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoOrganizerByDate {
    public static void main(String[] args) throws Exception {
        Path src = Paths.get(args.length>0?args[0]:"photos");
        Files.list(src).filter(p->p.toString().toLowerCase().matches(".*\\.(jpg|jpeg|png)")).forEach(p->{
            try {
                var meta = ImageMetadataReader.readMetadata(p.toFile());
                ExifSubIFDDirectory dir = meta.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                Date d = dir != null ? dir.getDateOriginal() : new Date(Files.getLastModifiedTime(p).toMillis());
                String folder = new SimpleDateFormat("yyyy/MM").format(d);
                Path targetDir = src.resolveSibling("organized").resolve(folder);
                Files.createDirectories(targetDir);
                Files.move(p, targetDir.resolve(p.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Moved " + p.getFileName() + " -> " + targetDir);
            } catch (Exception e) { System.out.println("Skip " + p + " (" + e.getMessage()+")"); }
        });
    }
}
