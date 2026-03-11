import java.util.Scanner;

public class TypingSpeed {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String sentence = "Java programming is powerful and flexible";

        System.out.println("Type this sentence:");
        System.out.println(sentence);

        long start = System.currentTimeMillis();

        String input = sc.nextLine();

        long end = System.currentTimeMillis();

        double time = (end-start)/1000.0;

        int words = sentence.split(" ").length;

        double wpm = (words/time)*60;

        System.out.println("Time: "+time+" seconds");
        System.out.println("Speed: "+wpm+" WPM");

    }
}
