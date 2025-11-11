import java.util.*;

public class NaiveBayesClassifier {
    static Map<String, Map<String, Integer>> wordCounts = new HashMap<>();
    static Map<String, Integer> classCounts = new HashMap<>();

    public static void train(String text, String label) {
        wordCounts.putIfAbsent(label, new HashMap<>());
        classCounts.put(label, classCounts.getOrDefault(label, 0) + 1);
        for (String word : text.toLowerCase().split("\\W+")) {
            wordCounts.get(label).put(word, wordCounts.get(label).getOrDefault(word, 0) + 1);
        }
    }

    public static String predict(String text) {
        double bestScore = Double.NEGATIVE_INFINITY;
        String bestLabel = null;
        for (String label : wordCounts.keySet()) {
            double score = Math.log(classCounts.get(label));
            for (String word : text.toLowerCase().split("\\W+")) {
                int count = wordCounts.get(label).getOrDefault(word, 0) + 1;
                score += Math.log(count);
            }
            if (score > bestScore) {
                bestScore = score;
                bestLabel = label;
            }
        }
        return bestLabel;
    }

    public static void main(String[] args) {
        train("I love this phone, it's amazing", "Positive");
        train("This is terrible and bad", "Negative");
        System.out.println(predict("I love this"));  // → Positive
        System.out.println(predict("This is bad")); // → Negative
    }
}
