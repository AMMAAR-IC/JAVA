import java.nio.file.*;

public class CharCountFile {

    public static void main(String[] args) throws Exception {

        String text=Files.readString(Path.of("file.txt"));

        System.out.println(text.length());
    }
}
