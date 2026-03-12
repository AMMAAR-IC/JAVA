import java.nio.file.*;

public class XOREncryptFile {

    public static void main(String[] args) throws Exception {

        byte key = 42;

        byte[] data = Files.readAllBytes(Path.of("input.txt"));

        for(int i=0;i<data.length;i++)
            data[i] ^= key;

        Files.write(Path.of("encrypted.bin"), data);

        System.out.println("Done");
    }
}
