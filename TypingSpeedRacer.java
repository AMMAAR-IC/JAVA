import java.util.Scanner;

public class TypingSpeedRacer {
    public static void main(String[] args) {
        String text = "The quick brown fox jumps over the lazy dog.";
        System.out.println("Type the following as fast as you can:\n");
        System.out.println(text);
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPress Enter to start...");
        sc.nextLine();
        
        long start = System.currentTimeMillis();
        String input = sc.nextLine();
        long end = System.currentTimeMillis();
        
        if (!input.equals(text)) {
            System.out.println("❌ Incorrect typing.");
            return;
        }
        
        double seconds = (end - start) / 1000.0;
        double wpm = (text.split(" ").length / seconds) * 60;
        
        System.out.printf("✅ You typed it correctly in %.2f seconds (%.2f WPM)\n", seconds, wpm);
    }
}
