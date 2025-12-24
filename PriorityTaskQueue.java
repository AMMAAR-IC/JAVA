import java.util.concurrent.*;

public class PriorityTaskQueue {
    static class Task implements Comparable<Task> {
        int p; String name;
        Task(int p, String n){ this.p=p; this.name=n; }
        public int compareTo(Task o){ return o.p - p; }
    }

    public static void main(String[] args) throws Exception {
        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>();
        q.put(new Task(1,"low"));
        q.put(new Task(10,"high"));
        q.put(new Task(5,"mid"));

        while(!q.isEmpty())
            System.out.println(q.take().name);
    }
}
