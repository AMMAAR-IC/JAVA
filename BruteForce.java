import java.util.*;

public class CaesarCracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter encrypted text: ");
        String cipher = sc.nextLine();

        String[] commonWords = {"the", "and", "this", "is", "of", "in", "you"};

        for (int shift = 1; shift < 26; shift++) {
            String decoded = decode(cipher, shift);
            int score = 0;
            for (String word : commonWords) {
                if (decoded.toLowerCase().contains(word)) score++;
            }

            if (score > 1) {
                System.out.println("🔓 Possible Match (Shift " + shift + "): " + decoded);
            }
        }
    }

    static String decode(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                sb.append((char) ((ch - base + shift) % 26 + base));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
