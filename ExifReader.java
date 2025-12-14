import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class ExifReader {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Path.of("image.jpg"));
        String s = new String(data);

        Pattern p = Pattern.compile("(Make|Model|DateTime)[^\\x00]+");
        Matcher m = p.matcher(s);

        while (m.find())
            System.out.println(m.group());
    }
}
