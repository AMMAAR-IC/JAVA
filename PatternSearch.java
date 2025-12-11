import java.nio.file.*;

public class PatternSearch {
    public static void main(String[] args) throws Exception {
        byte[] f = Files.readAllBytes(Path.of("file.bin"));
        byte[] sig = {0x50,0x4B,0x03,0x04}; // ZIP header

        for (int i=0;i<f.length-sig.length;i++) {
            boolean ok = true;
            for (int j=0;j<sig.length;j++)
                if (f[i+j]!=sig[j]) ok=false;

            if (ok) System.out.println("Signature at offset " + i);
        }
    }
}
