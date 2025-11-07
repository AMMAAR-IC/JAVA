import java.util.*;

public class LanguageDetector {
    public static void main(String[] args) {
        String text = "Bonjour, je m'appelle Ammaar et j'adore la programmation.";
        Map<String, String[]> langWords = Map.of(
            "English", new String[]{"the","is","and","you","to"},
            "French",  new String[]{"le","la","et","je","bonjour"},
            "Spanish", new String[]{"el","la","y","hola","soy"}
        );
        Map<String,Integer> score = new HashMap<>();
        for (var entry : langWords.entrySet()) {
            int c = 0;
            for (String w : entry.getValue())
                if (text.toLowerCase().contains(w)) c++;
            score.put(entry.getKey(), c);
        }
        String guess = score.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("Detected Language: " + guess);
    }
}
