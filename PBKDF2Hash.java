import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PBKDF2Hash {
    public static void main(String[] args)throws Exception{
        String pass="hello123";
        byte[] salt=new byte[16];
        new SecureRandom().nextBytes(salt);

        PBEKeySpec spec=new PBEKeySpec(pass.toCharArray(),salt,10000,256);
        byte[] hash=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec).getEncoded();

        System.out.println(Base64.getEncoder().encodeToString(hash));
    }
}
