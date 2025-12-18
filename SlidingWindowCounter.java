import java.util.*;

public class SlidingWindowCounter {
    static Queue<Long> q = new LinkedList<>();

    static boolean allow(int limit, long windowMs) {
        long now = System.currentTimeMillis();
        while (!q.isEmpty() && now - q.peek() > windowMs)
            q.poll();
        if (q.size() < limit) {
            q.add(now);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(allow(3, 1000));
    }
}
