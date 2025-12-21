import java.nio.file.*;

public class FilePerms {
    public static void main(String[] args) throws Exception {
        Path p = Path.of("test.sh");
        System.out.println("Readable: " + Files.isReadable(p));
        System.out.println("Writable: " + Files.isWritable(p));
        System.out.println("Executable: " + Files.isExecutable(p));
    }
}
