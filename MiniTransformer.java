import java.util.*;

public class MiniTransformer {

    // Mimic tokenizer
    static class SimpleTokenizer {
        public List<String> tokenize(String text) {
            text = text.toLowerCase().replaceAll("[^a-z ]", "");
            return Arrays.asList(text.split("\\s+"));
        }
    }

    // Mimic transformer model
    static class MiniTransformerModel {
        private final List<String> vocabulary;
        private final Random random;

        public MiniTransformerModel(List<String> vocab) {
            this.vocabulary = vocab;
            this.random = new Random();
        }

        public double[] getSentenceEmbedding(List<String> tokens) {
            double[] vector = new double[8]; // fake 8-dim embedding
            for (String token : tokens) {
                int idx = vocabulary.indexOf(token);
                if (idx != -1) {
                    for (int i = 0; i < vector.length; i++)
                        vector[i] += Math.sin(idx * 0.5 + i);  // fake math
                }
            }
            return vector;
        }

        public String classify(List<String> tokens) {
            int score = tokens.stream().mapToInt(String::length).sum();
            return score % 2 == 0 ? "Positive" : "Negative";
        }
    }

    // Pipeline
    public static void pipeline(String task, String inputText) {
        SimpleTokenizer tokenizer = new SimpleTokenizer();
        List<String> tokens = tokenizer.tokenize(inputText);

        List<String> vocab = Arrays.asList("i", "love", "java", "machine", "learning", "hate", "bad", "good");
        MiniTransformerModel model = new MiniTransformerModel(vocab);

        switch (task.toLowerCase()) {
            case "embedding":
                double[] embedding = model.getSentenceEmbedding(tokens);
                System.out.println("Sentence Embedding: " + Arrays.toString(embedding));
                break;
            case "sentiment":
                String result = model.classify(tokens);
                System.out.println("Sentiment: " + result);
                break;
            default:
                System.out.println("Unsupported task.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter task (sentiment / embedding): ");
        String task = sc.nextLine();

        System.out.print("Enter your text: ");
        String text = sc.nextLine();

        pipeline(task, text);
    }
}
