import java.util.*;
public class SentimentAnalyzer {
    public static void main(String[] args) {
        String text = "I love Java, but debugging can be frustrating.";
        Set<String> positive = Set.of("love", "great", "good", "amazing", "excellent");
        Set<String> negative = Set.of("bad", "terrible", "hate", "frustrating", "worst");
        int score = 0;
        for (String word : text.toLowerCase().split("\\W+")) {
            if (positive.contains(word)) score++;
            else if (negative.contains(word)) score--;
        }
        System.out.println("Text: " + text);
        System.out.println("Sentiment Score: " + score + " → " +
                (score > 0 ? "Positive" : score < 0 ? "Negative" : "Neutral"));
    }
}
