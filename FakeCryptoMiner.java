import java.security.*;
import java.util.Random;

public class FakeCryptoMiner {
    public static void main(String[] args) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        Random rand = new Random();
        int blocksMined = 0;

        System.out.println("⛏️ Mining... Press Ctrl+C to stop\n");

        while (true) {
            String input = "block" + rand.nextInt(1000000);
            byte[] hash = md.digest(input.getBytes());

            String hex = bytesToHex(hash);
            if (hex.startsWith("0000")) {
                System.out.println("💰 Block mined! Hash: " + hex + " (" + (++blocksMined) + ")");
                Thread.sleep(500);
            }
        }
    }

    static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
