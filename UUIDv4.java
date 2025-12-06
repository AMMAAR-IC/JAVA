import java.security.SecureRandom;

public class UUIDv4 {
    public static void main(String[] args) {
        SecureRandom r = new SecureRandom();
        byte[] b = new byte[16];
        r.nextBytes(b);

        b[6] = (byte)((b[6] & 0x0F) | 0x40); // version 4
        b[8] = (byte)((b[8] & 0x3F) | 0x80); // variant

        System.out.println(toHex(b));
    }

    static String toHex(byte[] b){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<b.length;i++) {
            sb.append(String.format("%02x", b[i]));
            if (i==3||i==5||i==7||i==9) sb.append("-");
        }
        return sb.toString();
    }
}
