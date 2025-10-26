import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class AutoBackup {
    public static void main(String[] args) throws Exception {
        Path src = Path.of("C:/Projects/Main");
        Path dest = Path.of("C:/Backup/Main");
        Files.createDirectories(dest);
        Files.walk(src).filter(Files::isRegularFile).forEach(f -> {
            try {
                Path target = dest.resolve(src.relativize(f));
                if (!Files.exists(target) || Files.getLastModifiedTime(f).compareTo(Files.getLastModifiedTime(target)) > 0) {
                    Files.createDirectories(target.getParent());
                    Files.copy(f, target, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied: " + f);
                }
            } catch (Exception ignored) {}
        });
    }
}
