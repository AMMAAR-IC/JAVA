import java.nio.file.*;
import java.util.*;

public class FileSorter {

    public static void main(String[] args) throws Exception {

        List<String> lines = Files.readAllLines(Path.of("data.txt"));

        Collections.sort(lines);

        Files.write(Path.of("sorted.txt"), lines);
    }
}
