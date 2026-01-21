import java.util.*;

public class RetryQueue {
    static Queue<Runnable> q = new LinkedList<>();

    public static void main(String[] args) {
        q.add(() -> System.out.println("Task 1"));
        q.add(() -> System.out.println("Task 2"));

        while (!q.isEmpty())
            q.poll().run();
    }
}
