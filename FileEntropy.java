import java.nio.file.*;

public class FileEntropy {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Path.of("data.bin"));
        int[] freq = new int[256];

        for(byte b : data) freq[b & 255]++;

        double ent = 0;
        for(int f : freq){
            if(f == 0) continue;
            double p = f / (double)data.length;
            ent -= p * (Math.log(p)/Math.log(2));
        }
        System.out.println("Entropy: " + ent);
    }
}
