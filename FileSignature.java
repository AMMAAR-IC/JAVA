import java.nio.file.*;

public class FileSignature {
    public static void main(String[] args) throws Exception {
        byte[] b = Files.readAllBytes(Path.of("file.bin"));
        System.out.println(b[0]==(byte)0xFF && b[1]==(byte)0xD8 ? "JPEG" : "Unknown");
    }
}
