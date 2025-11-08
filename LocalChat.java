import java.util.*;
public class LocalChat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("User1: ");
            String u1 = sc.nextLine();
            if (u1.equalsIgnoreCase("exit")) break;
            System.out.print("User2: ");
            String u2 = sc.nextLine();
            System.out.println("User1 -> " + u1);
            System.out.println("User2 -> " + u2);
            System.out.println("------------------");
        }
    }
}
