import java.nio.file.*;

public class WordCountFile {

    public static void main(String[] args) throws Exception {

        String text=Files.readString(Path.of("file.txt"));

        System.out.println(text.split("\\s+").length);
    }
}
