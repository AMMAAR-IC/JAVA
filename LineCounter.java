import java.nio.file.*;

public class LineCounter {
    public static void main(String[] args) throws Exception {
        long c = Files.lines(Path.of("data.txt")).count();
        System.out.println("Lines: " + c);
    }
}
