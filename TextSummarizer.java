import java.util.*;
public class TextSummarizer {
    public static void main(String[] args) {
        String text = "Artificial Intelligence is shaping the future of humanity. AI is revolutionizing industries. It enhances automation and decision-making.";
        String[] sentences = text.split("\\.");
        Map<String, Integer> freq = new HashMap<>();

        for (String s : text.toLowerCase().split("\\W+")) 
            freq.put(s, freq.getOrDefault(s, 0) + 1);

        Map<String, Double> scores = new HashMap<>();
        for (String sentence : sentences) {
            double score = 0;
            for (String word : sentence.toLowerCase().split("\\W+"))
                score += freq.getOrDefault(word, 0);
            scores.put(sentence, score);
        }

        sentences = Arrays.stream(sentences)
                .sorted((a, b) -> Double.compare(scores.get(b), scores.get(a)))
                .toArray(String[]::new);

        System.out.println("Summary: " + sentences[0].trim() + ".");
    }
}
