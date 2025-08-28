import java.util.*;

public class PasswordGen {
    public static void main(String[] args) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        Random r = new Random();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < 12; i++) pass.append(chars.charAt(r.nextInt(chars.length())));
        System.out.println("Generated Password: " + pass);
    }
}
