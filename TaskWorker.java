import java.util.concurrent.*;

public class TaskWorker {
    static BlockingQueue<Runnable> q = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            while(true) try { q.take().run(); } catch(Exception ignored){}
        }).start();

        enqueue(() -> System.out.println("Job_1"));
        enqueue(() -> System.out.println("Job_2 completed"));
    }

    static void enqueue(Runnable job){ q.offer(job); }
}
