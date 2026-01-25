import java.util.*;

public class BoundedQueue<T> {
    private Queue<T> q = new LinkedList<>();
    private int cap;

    public BoundedQueue(int cap){ this.cap = cap; }

    public synchronized void put(T x) throws InterruptedException {
        while(q.size() == cap) wait();
        q.add(x);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while(q.isEmpty()) wait();
        T v = q.poll();
        notifyAll();
        return v;
    }

    public static void main(String[] args) throws Exception {
        BoundedQueue<Integer> b = new BoundedQueue<>(2);
        new Thread(() -> { try { b.put(1); b.put(2); b.put(3); } catch(Exception e){} }).start();
        Thread.sleep(500);
        System.out.println(b.take());
        System.out.println(b.take());
    }
}
