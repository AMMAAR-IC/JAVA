import java.security.SecureRandom;
import java.util.HashMap;

public class EntropyVisualizer {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        HashMap<Integer, Integer> freq = new HashMap<>();
        int total = 100000;
        for (int i = 0; i < total; i++) {
            int n = random.nextInt(256);
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        double entropy = 0;
        for (int f : freq.values()) {
            double p = (double) f / total;
            entropy -= p * (Math.log(p) / Math.log(2));
        }
        System.out.printf("System Entropy: %.4f bits per byte%n", entropy);
    }
}
