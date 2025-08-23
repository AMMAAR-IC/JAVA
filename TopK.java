import java.util.*;

public class TopK {
    private final PriorityQueue<Integer> pq;
    private final int k;

    public TopK(int k) {
        this.k = k;
        this.pq = new PriorityQueue<>(k);
    }

    public void add(int num) {
        if (pq.size() < k) pq.add(num);
        else if (num > pq.peek()) { pq.poll(); pq.add(num); }
    }

    public List<Integer> get() {
        List<Integer> list = new ArrayList<>(pq);
        list.sort(Collections.reverseOrder());
        return list;
    }

    public static void main(String[] args) {
        TopK top3 = new TopK(3);
        int[] stream = {5, 1, 9, 3, 14, 7, 2, 20};
        for (int x : stream) top3.add(x);
        System.out.println("Top 3: " + top3.get()); // [20,14,9]
    }
}
