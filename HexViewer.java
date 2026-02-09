import java.nio.file.*;

public class HexViewer {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Path.of("file.bin"));

        for(int i=0;i<data.length;i++){
            if(i%16==0) System.out.printf("\n%08x: ", i);
            System.out.printf("%02x ", data[i]);
        }
    }
}
