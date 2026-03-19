import java.nio.file.*;

public class FileAppend {

    public static void main(String[] args) throws Exception {

        Files.writeString(
                Path.of("file.txt"),
                "\nNew Line",
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE
        );
    }
}
