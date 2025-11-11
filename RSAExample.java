import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAExample {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair kp = gen.generateKeyPair();

        String msg = "Hello, RSA!";
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE, kp.getPublic());
        byte[] enc = c.doFinal(msg.getBytes());
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(enc));

        c.init(Cipher.DECRYPT_MODE, kp.getPrivate());
        System.out.println("Decrypted: " + new String(c.doFinal(enc)));
    }
}
