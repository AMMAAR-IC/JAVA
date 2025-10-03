// TOTPGenerator.java
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.time.Instant;

public class TOTPGenerator {
    public static String generateTOTP(byte[] key, long timeStepSeconds, int digits) throws Exception {
        long t = Instant.now().getEpochSecond() / timeStepSeconds;
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putLong(t);
        byte[] data = bb.array();

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(key, "HmacSHA1"));
        byte[] hash = mac.doFinal(data);

        int offset = hash[hash.length - 1] & 0x0F;
        int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset+1] & 0xff) << 16) |
                     ((hash[offset+2] & 0xff) << 8) | (hash[offset+3] & 0xff);
        int otp = binary % (int) Math.pow(10, digits);
        return String.format("%0" + digits + "d", otp);
    }

    public static void main(String[] args) throws Exception {
        String base32Secret = "JBSWY3DPEHPK3PXP"; // example (base32)
        byte[] key = java.util.Base64.getDecoder().decode(java.util.Base64.getEncoder().encodeToString(base32Secret.getBytes()));
        System.out.println("TOTP: " + generateTOTP(key, 30, 6));
    }
}
