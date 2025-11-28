import java.security.*;
import java.util.Base64;

public class RSAKeyGen {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        String pub = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
        String pri = Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded());

        System.out.println("-----BEGIN PUBLIC KEY-----");
        System.out.println(pub);
        System.out.println("-----END PUBLIC KEY-----");

        System.out.println("\n-----BEGIN PRIVATE KEY-----");
        System.out.println(pri);
        System.out.println("-----END PRIVATE KEY-----");
    }
}
