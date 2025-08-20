import java.util.Random;
import java.util.Scanner;

public class Magic8Ball {
    public static void main(String[] args) {
        String[] responses = {
            "Yes", "No", "Maybe", "Definitely", 
            "Ask again later", "Not sure", "Of course!", "Never"
        };
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Ask the Magic 8-Ball anything:");
        while (true) {
            sc.nextLine();
            System.out.println("🎱 " + responses[rand.nextInt(responses.length)]);
        }
    }
}
