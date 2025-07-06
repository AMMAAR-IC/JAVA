import java.util.*;
import java.util.regex.*;
import java.io.*;

public class PasswordStrengthAnalyzer {

    private static final Set<String> commonPasswords = new HashSet<>();

    static {
        // Load common passwords (you can replace or expand this)
        commonPasswords.add("123456");
        commonPasswords.add("password");
        commonPasswords.add("123456789");
        commonPasswords.add("qwerty");
        commonPasswords.add("abc123");
        commonPasswords.add("111111");
        commonPasswords.add("123123");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        analyzePassword(password);
    }

    public static void analyzePassword(String password) {
        int score = 0;
        int length = password.length();

        System.out.println("\n🔍 Password Analysis:");

        // Length check
        if (length >= 12) {
            System.out.println("✔️ Good length.");
            score += 2;
        } else if (length >= 8) {
            System.out.println("⚠️ Moderate length.");
            score += 1;
        } else {
            System.out.println("❌ Too short.");
        }

        // Uppercase, lowercase, digits, symbols
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*\\d.*")) score++;
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) score++;

        // Sequential check
        if (hasSequentialChars(password)) {
            System.out.println("❌ Contains sequential characters.");
        } else {
            score++;
        }

        // Keyboard pattern
        if (matchesKeyboardPattern(password)) {
            System.out.println("❌ Contains keyboard pattern.");
        } else {
            score++;
        }

        // Common passwords
        if (commonPasswords.contains(password.toLowerCase())) {
            System.out.println("❌ Found in common password list.");
        } else {
            score++;
        }

        // Entropy estimate
        double entropy = estimateEntropy(password);
        System.out.printf("🧠 Estimated Entropy: %.2f bits%n", entropy);
        if (entropy > 60) score++;
        else if (entropy > 40) score += 0.5;

        // Final score
        System.out.println("\n🔒 Strength Score: " + score + "/10");

        if (score >= 9) {
            System.out.println("🟢 Very Strong Password");
        } else if (score >= 7) {
            System.out.println("🟡 Strong Password");
        } else if (score >= 5) {
            System.out.println("🟠 Weak Password");
        } else {
            System.out.println("🔴 Very Weak Password");
        }
    }

    private static boolean hasSequentialChars(String password) {
        String lower = password.toLowerCase();
        for (int i = 0; i < lower.length() - 2; i++) {
            char a = lower.charAt(i);
            char b = lower.charAt(i + 1);
            char c = lower.charAt(i + 2);
            if (b == a + 1 && c == b + 1) return true;
        }
        return false;
    }

    private static boolean matchesKeyboardPattern(String password) {
        String[] patterns = {
            "qwerty", "asdf", "zxcv", "1234", "password", "letmein", "admin"
        };
        String lower = password.toLowerCase();
        for (String pattern : patterns) {
            if (lower.contains(pattern)) return true;
        }
        return false;
    }

    private static double estimateEntropy(String password) {
        int pool = 0;
        if (password.matches(".*[a-z].*")) pool += 26;
        if (password.matches(".*[A-Z].*")) pool += 26;
        if (password.matches(".*\\d.*")) pool += 10;
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) pool += 32;
        return password.length() * (Math.log(pool) / Math.log(2));
    }
}
