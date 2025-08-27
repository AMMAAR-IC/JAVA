import java.nio.file.*; import java.io.*; import java.util.*;

public class MiniWC {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) { System.out.println("Usage: java MiniWC <file>"); return; }
        String content = Files.readString(Path.of(args[0]));
        long lines = content.lines().count();
        long words = Arrays.stream(content.split("\\s+")).filter(w->!w.isEmpty()).count();
        long chars = content.length();
        System.out.printf("%d %d %d %s\n", lines, words, chars, args[0]);
    }
}
