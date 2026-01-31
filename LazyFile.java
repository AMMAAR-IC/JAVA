import java.nio.file.*;

public class LazyFile {
    static String data;

    static String get() throws Exception {
        if (data == null)
            data = Files.readString(Path.of("file.txt"));
        return data;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(get());
    }
}
