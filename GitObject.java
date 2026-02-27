import java.security.*;

public class GitObject {
    public static void main(String[] args) throws Exception {
        String content="hello world";

        MessageDigest md =
                MessageDigest.getInstance("SHA-1");

        byte[] hash=md.digest(content.getBytes());

        for(byte b:hash)
            System.out.printf("%02x",b);
    }
}
