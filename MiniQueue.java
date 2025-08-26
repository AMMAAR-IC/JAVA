import java.util.*;

public class MiniQueue {
    private final Queue<String> q = new LinkedList<>();

    public void publish(String msg) { q.add(msg); }
    public String consume() { return q.poll(); }

    public static void main(String[] args) {
        MiniQueue mq = new MiniQueue();
        mq.publish("task1");
        mq.publish("task2");
        System.out.println(mq.consume());
        System.out.println(mq.consume());
    }
}
