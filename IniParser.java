import java.nio.file.*;
import java.util.*;

public class IniParser {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("config.ini"));
        Map<String, Map<String,String>> out = new HashMap<>();

        String section = "";
        for (String l : lines) {
            l = l.trim();
            if (l.isEmpty() || l.startsWith(";")) continue;
            if (l.startsWith("[") && l.endsWith("]")) {
                section = l.substring(1,l.length()-1);
                out.put(section,new HashMap<>());
            } else if (l.contains("=")) {
                String[] p = l.split("=",2);
                out.get(section).put(p[0].trim(), p[1].trim());
            }
        }

        System.out.println(out);
    }
}
