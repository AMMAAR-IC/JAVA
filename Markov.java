import java.util.*;

public class StoryGenerator {
    public static void main(String[] args) {
        String training = "The hero entered the dark forest. The forest was silent. The hero had a sword. The sword glowed. The forest was cursed.";

        Map<String, List<String>> markov = new HashMap<>();
        String[] words = training.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            String key = words[i];
            markov.putIfAbsent(key, new ArrayList<>());
            markov.get(key).add(words[i + 1]);
        }

        Random rand = new Random();
        String word = "The";
        System.out.print("📖 Story: " + word);

        for (int i = 0; i < 30; i++) {
            List<String> nextWords = markov.getOrDefault(word, List.of("the"));
            word = nextWords.get(rand.nextInt(nextWords.size()));
            System.out.print(" " + word);
        }
        System.out.println(".");
    }
}
