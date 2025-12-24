import java.nio.file.*;

public class FileGrep {
    public static void main(String[] args) throws Exception {
        for (String l : Files.readAllLines(Path.of("data.txt")))
            if (l.contains("java"))
                System.out.println(l);
    }
}
