import java.util.*;

public class WisdomBot {
    static String[] quotes = {
        "The unexamined life is not worth living. — Socrates",
        "We suffer more often in imagination than in reality. — Seneca",
        "He who opens a school door, closes a prison. — Victor Hugo",
        "The only true wisdom is in knowing you know nothing. — Socrates",
        "Man is condemned to be free. — Sartre"
    };

    public static void main(String[] args) {
        System.out.println("🧠 Philosophical Quote of the Day:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(getRandomQuote());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    static String getRandomQuote() {
        return quotes[new Random().nextInt(quotes.length)];
    }
