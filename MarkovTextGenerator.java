import java.util.*;

public class MarkovTextGenerator {
    private static Map<String, List<String>> markov = new HashMap<>();

    public static void train(String text) {
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length - 1; i++) {
            markov.putIfAbsent(words[i], new ArrayList<>());
            markov.get(words[i]).add(words[i + 1]);
        }
    }

    public static String generate(String start, int length) {
        StringBuilder sb = new StringBuilder(start);
        String word = start;
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            List<String> nextWords = markov.get(word);
            if (nextWords == null || nextWords.isEmpty()) break;
            word = nextWords.get(rand.nextInt(nextWords.size()));
            sb.append(" ").append(word);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String sample = "Java is powerful. Java is versatile. Java is everywhere.";
        train(sample);

        System.out.println("🧠 Generated Text:");
        System.out.println(generate("Java", 20));
    }
}
