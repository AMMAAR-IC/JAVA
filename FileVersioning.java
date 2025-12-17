import java.nio.file.*;
import java.time.*;

public class FileVersioning {
    public static void main(String[] args) throws Exception {
        Path src = Path.of("data.txt");
        Path backup = Path.of("versions",
                "data_" + LocalDateTime.now().toString().replace(":","-") + ".txt");

        Files.createDirectories(backup.getParent());
        Files.copy(src, backup);

        System.out.println("Snapshot saved: " + backup);
    }
}
