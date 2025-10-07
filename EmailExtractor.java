// EmailExtractor.java
import java.nio.file.*;
import java.util.regex.*;

public class EmailExtractor {
    public static void main(String[] args) throws Exception {
        String data = Files.readString(Path.of("sample.txt"));
        Matcher m = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+").matcher(data);
        System.out.println("📧 Emails found:");
        while (m.find()) System.out.println(m.group());
    }
}
