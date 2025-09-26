import java.util.*;

public class SpellChecker {
    private static final Set<String> dictionary = new HashSet<>(Arrays.asList(
            "java", "programming", "hello", "world", "example"
    ));

    public static void main(String[] args) {
        String text = "helo world javaa";
        for (String word : text.split(" ")) {
            if (!dictionary.contains(word)) {
                System.out.println("Misspelled: " + word);
            }
        }
    }
}
