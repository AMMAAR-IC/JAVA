import java.nio.file.*;
import java.util.*;

public class KeywordExtractor {
    public static void main(String[] args) throws Exception {
        String text = Files.readString(Path.of("article.txt")).toLowerCase();
        String[] docs = text.split("\\.");
        Map<String, Integer> df = new HashMap<>();
        List<Map<String, Double>> tfs = new ArrayList<>();

        for (String d : docs) {
            Map<String, Double> tf = new HashMap<>();
            String[] words = d.split("\\W+");
            for (String w : words) {
                if (w.isEmpty()) continue;
                tf.put(w, tf.getOrDefault(w, 0.0) + 1);
            }
            for (String w : tf.keySet()) df.put(w, df.getOrDefault(w, 0) + 1);
            tfs.add(tf);
        }

        int N = docs.length;
        Map<String, Double> tfidf = new HashMap<>();
        for (var tf : tfs)
            for (var e : tf.entrySet()) {
                double idf = Math.log((double) N / (1 + df.get(e.getKey())));
                tfidf.merge(e.getKey(), e.getValue() * idf, Double::sum);
            }

        tfidf.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(10)
                .forEach(e -> System.out.printf("%s → %.3f%n", e.getKey(), e.getValue()));
    }
}
