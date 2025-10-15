import java.nio.file.*;
import java.util.*;

public class WordCloud {
    public static void main(String[] args) throws Exception {
        String text = Files.readString(Path.of("sample.txt")).toLowerCase();
        String[] words = text.replaceAll("[^a-z ]", "").split("\\s+");
        Map<String,Integer> freq = new HashMap<>();
        for (String w : words) freq.put(w, freq.getOrDefault(w,0)+1);

        freq.entrySet().stream()
            .sorted((a,b)->b.getValue()-a.getValue())
            .limit(20)
            .forEach(e->System.out.println(e.getKey()+": "+e.getValue()));
    }
}
