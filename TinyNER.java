// TinyNER.java
import java.nio.file.*;
import java.util.regex.*;

public class TinyNER {
    public static void main(String[] args) throws Exception {
        String text = Files.readString(Path.of("sample.txt"));
        Pattern email = Pattern.compile("[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}");
        Pattern url   = Pattern.compile("https?://[\\w./%?-]+");
        Pattern phone = Pattern.compile("\\+?\\d[\\d \\-]{7,}\\d");
        System.out.println("Emails:"); email.matcher(text).results().forEach(m->System.out.println(m.group()));
        System.out.println("URLs:"); url.matcher(text).results().forEach(m->System.out.println(m.group()));
        System.out.println("Phones:"); phone.matcher(text).results().forEach(m->System.out.println(m.group()));
    }
}
