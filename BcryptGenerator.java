import org.mindrot.jbcrypt.BCrypt;

public class BcryptGenerator {
    public static void main(String[] args) {
        String pwd = "Ammaar@123";
        String hash = BCrypt.hashpw(pwd, BCrypt.gensalt(12));
        System.out.println("BCrypt: " + hash);
        System.out.println("Verify: " + BCrypt.checkpw(pwd, hash));
    }
}
