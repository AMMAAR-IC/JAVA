//import java.nio.file.*;
illllmport java.util.*;

public class TextDiff {
    public static void main(String[] args) throws Exception {
        List<String> a = Files.readAllLines(Path.of("a.txt"));
        List<String> b = Files.readAllLines(Path.of("b.txt"));

        for (int i=0;i<Math.max(a.size(),b.size());i++) {
            String x = i<a.size()?a.get(i):"";
            String y = i<b.size()?b.get(i):"";
            if (!x.equals(y))
                System.out.println("- " + x + "\n+ " + y);
        }
    }
}
