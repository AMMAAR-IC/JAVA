import java.util.*;

public class EmotionClassifier {
    Map<String, Map<String, Integer>> wordCounts = new HashMap<>();
    Map<String, Integer> labelCounts = new HashMap<>();
    Set<String> vocabulary = new HashSet<>();

    public void train(String sentence, String label) {
        labelCounts.put(label, labelCounts.getOrDefault(label, 0) + 1);
        Map<String, Integer> labelMap = wordCounts.computeIfAbsent(label, k -> new HashMap<>());
        for (String word : sentence.toLowerCase().split("\\W+")) {
            vocabulary.add(word);
            labelMap.put(word, labelMap.getOrDefault(word, 0) + 1);
        }
    }

    public String predict(String sentence) {
        double maxProb = Double.NEGATIVE_INFINITY;
        String bestLabel = null;

        for (String label : labelCounts.keySet()) {
            double logProb = Math.log(labelCounts.get(label));
            for (String word : sentence.toLowerCase().split("\\W+")) {
                int wordFreq = wordCounts.get(label).getOrDefault(word, 0) + 1;
                int total = wordCounts.get(label).values().stream().mapToInt(i -> i).sum() + vocabulary.size();
                logProb += Math.log((double) wordFreq / total);
            }
            if (logProb > maxProb) {
                maxProb = logProb;
                bestLabel = label;
            }
        }
        return bestLabel;
    }

    public static void main(String[] args) {
        EmotionClassifier clf = new EmotionClassifier();
        clf.train("I am so happy today", "happy");
        clf.train("This is the worst day ever", "sad");
        clf.train("I'm really angry about the delay", "angry");

        System.out.println(clf.predict("I am feeling amazing"));    // happy
        System.out.println(clf.predict("Why is everything bad"));   // sad
        System.out.println(clf.predict("This makes me furious"));   // angry
    }
}
