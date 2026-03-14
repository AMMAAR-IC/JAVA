import java.nio.file.*;

public class ReverseFile {

    public static void main(String[] args) throws Exception {

        String text = Files.readString(Path.of("file.txt"));

        String rev = new StringBuilder(text).reverse().toString();

        Files.writeString(Path.of("rev.txt"), rev);
    }
}
