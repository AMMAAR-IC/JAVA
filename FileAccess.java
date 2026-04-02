import java.nio.file.*;
import java.nio.file.attribute.*;

public class FileAccess {

    public static void main(String[] args) throws Exception {

        Path p=Path.of("file.txt");

        System.out.println(
            Files.readAttributes(p,
                BasicFileAttributes.class)
                .lastAccessTime()
        );
    }
}
