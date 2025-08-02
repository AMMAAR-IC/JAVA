import java.util.*;

public class MarkovChatBot {
    Map<String, List<String>> chain = new HashMap<>();
    Random random = new Random();

    public void train(String text) {
        String[] words = text.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            chain.computeIfAbsent(words[i], k -> new ArrayList<>()).add(words[i + 1]);
        }
    }

    public String generate(String start, int length) {
        StringBuilder result = new StringBuilder(start);
        String current = start;

        for (int i = 0; i < length; i++) {
            List<String> nextWords = chain.get(current);
            if (nextWords == null || nextWords.isEmpty()) break;
            current = nextWords.get(random.nextInt(nextWords.size()));
            result.append(" ").append(current);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        MarkovChatBot bot = new MarkovChatBot();
        String inputText = "AI is transforming the world. AI can write code, draw art, and help people. The future of AI is fascinating.";
        bot.train(inputText);
        System.out.println(bot.generate("AI", 10));
    }
}
