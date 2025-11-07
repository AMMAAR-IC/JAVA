import java.io.*;
import java.nio.file.*;

public class FileOrganizer {
    public static void main(String[] args) throws Exception {
        Path dir = Path.of("Downloads");
        Files.list(dir).filter(Files::isRegularFile).forEach(f -> {
            String ext = f.getFileName().toString().replaceAll(".*\\.", "").toLowerCase();
            Path targetDir = dir.resolve(ext + "_files");
            try {
                Files.createDirectories(targetDir);
                Files.move(f, targetDir.resolve(f.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Moved: " + f + " → " + targetDir);
            } catch (IOException e) { e.printStackTrace(); }
        });
    }
}
