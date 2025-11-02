// MdLinter.java
import java.nio.file.*;
import java.util.*;
public class MdLinter {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("README.md"));
        if (lines.isEmpty() || !lines.get(0).startsWith("# ")) System.out.println("Warning: Missing H1 title at top");
        int lastLevel = 0;
        for (String l : lines) {
            if (l.matches("^#+ .*")) {
                int level = l.indexOf(' ');
                if (level - lastLevel > 1) System.out.println("Heading jump: " + l);
                lastLevel = level;
            }
        }
    }
}
