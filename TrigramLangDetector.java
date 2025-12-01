import java.nio.file.*;
import java.util.*;

public class TrigramLangDetector {
    static Map<String, Map<String,Integer>> models = new HashMap<>();

    public static void main(String[] args) throws Exception {
        models.put("en", build("sample_en.txt"));
        models.put("fr", build("sample_fr.txt"));
        String text = Files.readString(Path.of("unknown.txt")).toLowerCase();
        String best = null; double bestScore = Double.NEGATIVE_INFINITY;
        for (var e: models.entrySet()) {
            double sc = score(e.getValue(), text);
            if (sc > bestScore) { bestScore = sc; best = e.getKey(); }
        }
        System.out.println("Detected: " + best);
    }

    static Map<String,Integer> build(String file) throws Exception {
        String s = Files.readString(Path.of(file)).toLowerCase().replaceAll("\\s+", " ");
        Map<String,Integer> m = new HashMap<>();
        for (int i=0;i<s.length()-2;i++) m.merge(s.substring(i,i+3),1,Integer::sum);
        return m;
    }

    static double score(Map<String,Integer> model, String text) {
        double sc = 0;
        for (int i=0;i<text.length()-2;i++) sc += Math.log(1 + model.getOrDefault(text.substring(i,i+3), 0));
        return sc;
    }
}
