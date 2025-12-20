import java.util.*;

public class RollingAverage {
    static Queue<Integer> q = new LinkedList<>();
    static int sum = 0;

    static double add(int v, int k) {
        q.add(v);
        sum += v;
        if (q.size() > k) sum -= q.poll();
        return sum / (double) q.size();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++)
            System.out.println(add(i, 3));
    }
}
