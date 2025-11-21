// NGramPredictor.java
import java.nio.file.*;
import java.util.*;

public class NGramPredictor {
    static Map<String, Map<String, Integer>> model = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String text = Files.readString(Path.of("sample.txt")).toLowerCase();
        String[] words = text.split("\\W+");

        for (int i=0;i<words.length-1;i++) {
            model.putIfAbsent(words[i], new HashMap<>());
            model.get(words[i]).merge(words[i+1], 1, Integer::sum);
        }

        String w = "machine";
        System.out.println("Next words for '" + w + "':");
        model.getOrDefault(w, Map.of()).entrySet().stream()
                .sorted((a,b)->b.getValue()-a.getValue())
                .limit(5)
                .forEach(e -> System.out.println(e.getKey()+" ("+e.getValue()+")"));
    }
}
