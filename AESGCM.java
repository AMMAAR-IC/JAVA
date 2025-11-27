// AESGCM.java
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;

public class AESGCM {
    public static void main(String[] args) throws Exception {
        byte[] key = new byte[16];
        byte[] iv = new byte[12];
        new Random().nextBytes(key);
        new Random().nextBytes(iv);

        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec ks = new SecretKeySpec(key, "AES");
        GCMParameterSpec gps = new GCMParameterSpec(128, iv);
        c.init(Cipher.ENCRYPT_MODE, ks, gps);

        byte[] data = "Hello Ammaar".getBytes();
        byte[] enc = c.doFinal(data);

        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(enc));

        c.init(Cipher.DECRYPT_MODE, ks, gps);
        byte[] dec = c.doFinal(enc);

        System.out.println("Decrypted: " + new String(dec));
    }
}
