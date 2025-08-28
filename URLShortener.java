import java.util.*;

public class URLShortener {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Map<String, String> db = new HashMap<>();

    public static String shorten(String url) {
        int hash = url.hashCode();
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(ALPHABET.charAt(Math.abs(hash + i) % ALPHABET.length()));
        }
        db.put(shortUrl.toString(), url);
        return shortUrl.toString();
    }

    public static String expand(String key) {
        return db.getOrDefault(key, "Not found!");
    }

    public static void main(String[] args) {
        String original = "https://github.com/Ammaar-Bakshi";
        String tiny = shorten(original);
        System.out.println("Shortened: " + tiny);
        System.out.println("Expanded: " + expand(tiny));
    }
}
