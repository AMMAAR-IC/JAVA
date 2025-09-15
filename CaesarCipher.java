// CaesarCipher.java
public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                sb.append((char)((c - base + shift) % 26 + base));
            } else sb.append(c);
        }
        return sb.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        String msg = "Hello Java!";
        String enc = encrypt(msg, 3);
        String dec = decrypt(enc, 3);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}
