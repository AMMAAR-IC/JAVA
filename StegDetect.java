import java.io.File;
import java.io.FileInputStream;

public class StegDetect {
    public static void main(String[] args) throws Exception {
        File file = new File("suspicious.png");
        try (FileInputStream in = new FileInputStream(file)) {
            long size = file.length();
            byte[] bytes = in.readAllBytes();
            int noise = 0;
            for (int i = 0; i < bytes.length - 1; i++) {
                if ((bytes[i] & 1) != (bytes[i + 1] & 1)) noise++;
            }
            double randomness = (double) noise / size;
            System.out.printf("Noise Ratio: %.4f -> %s%n", randomness,
                    randomness > 0.48 ? "Possible Hidden Data" : "Clean Image");
        }
    }
}
