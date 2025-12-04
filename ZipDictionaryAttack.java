import java.io.*;
import java.util.zip.*;

public class ZipDictionaryAttack {
    public static void main(String[] args) throws Exception {
        String[] passwords = {"admin","1234","password","ammaar","root"};
        File f = new File("secret.zip");

        for (String p : passwords) {
            try (ZipFile z = new ZipFile(f, p.toCharArray())) {
                System.out.println("Password found: " + p);
                return;
            } catch (Exception ignored) {}
        }
        System.out.println("No password matched.");
    }
}
