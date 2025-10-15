import java.nio.file.*;
import java.util.*;

public class SentimentAnalysis {
    public static void main(String[] args) throws Exception {
        Set<String> pos = Set.of("good","great","happy","love","excellent","fantastic");
        Set<String> neg = Set.of("bad","sad","hate","terrible","worst","angry");

        String text = Files.readString(Path.of("review.txt")).toLowerCase();
        int score = 0;
        for (String word : text.split("\\W+")) {
            if (pos.contains(word)) score++;
            if (neg.contains(word)) score--;
        }
        System.out.println("Sentiment Score: " + score + (score > 0 ? " 😊 Positive" : score < 0 ? " 😞 Negative" : " 😐 Neutral"));
    }
}
