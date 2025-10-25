import java.util.*;

public class ChatSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                System.out.print("You: ");
                String msg = sc.nextLine();
                System.out.println("Friend: " + reverseResponse(msg));
            }
        }).start();
    }

    private static String reverseResponse(String msg) {
        return new StringBuilder(msg).reverse().toString();
    }
}
