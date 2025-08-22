import java.util.*;

public class TodoCLI {
    private static final List<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("TodoCLI (add <task>, list, rm <id>, exit)");
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.equals("exit")) break;
            else if (line.startsWith("add ")) {
                tasks.add(line.substring(4));
                System.out.println("Added.");
            } else if (line.equals("list")) {
                if (tasks.isEmpty()) System.out.println("(no tasks)");
                else for (int i=0; i<tasks.size(); i++)
                    System.out.printf("%d) %s%n", i+1, tasks.get(i));
            } else if (line.startsWith("rm ")) {
                try {
                    int idx = Integer.parseInt(line.substring(3)) - 1;
                    System.out.println("Removed: " + tasks.remove(idx));
                } catch (Exception e) {
                    System.out.println("Invalid id.");
                }
            } else System.out.println("Unknown command.");
        }
    }
}
