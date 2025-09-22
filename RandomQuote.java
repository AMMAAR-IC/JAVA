import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RandomQuote {

    private static final String QUOTES_FILE = "quotes.txt";

    public static void main(String[] args) {
        List<String> quotes = loadQuotes();

        if (quotes.isEmpty()) {
            System.out.println("No quotes available. Add a quote first.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nPress Enter to get a random quote (or type \"add\" to add a new quote, \"exit\" to quit):");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                break;
            } else if (input.equalsIgnoreCase("add")) {
                System.out.print("Enter the quote: ");
                String newQuote = sc.nextLine();
                if (!newQuote.isBlank()) {
                    quotes.add(newQuote);
                    saveQuote(newQuote);
                    System.out.println("Quote added.");
                } else {
                    System.out.println("Empty quote not added.");
                }
            } else {
                String randomQuote = quotes.get(new Random().nextInt(quotes.size()));
                System.out.println("\n💬 " + randomQuote);
            }
        }

        sc.close();
    }

    private static List<String> loadQuotes() {
        try {
            Path path = Paths.get(QUOTES_FILE);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Error reading quotes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void saveQuote(String quote) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(QUOTES_FILE, true))) {
            writer.write(quote);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving quote: " + e.getMessage());
        }
    }
}
