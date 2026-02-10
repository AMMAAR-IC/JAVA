import java.nio.file.*;

public class LogPattern {
    public static void main(String[] args) throws Exception {
        for(String line:Files.readAllLines(Path.of("app.log")))
            if(line.contains("ERROR") || line.contains("FAILED"))
                System.out.println(line);
    }
}
