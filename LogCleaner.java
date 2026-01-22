import java.nio.file.*;

public class LogCleaner {
    public static void main(String[] args) throws Exception {
        var lines = Files.readAllLines(Path.of("app.log"));
        lines.removeIf(String::isBlank);
        Files.write(Path.of("clean.log"), lines);
    }
}
