import java.util.concurrent.*;

public class MiniThreadPool {
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final Thread[] workers;

    public MiniThreadPool(int nThreads) {
        workers = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            workers[i] = new Thread(() -> {
                try {
                    while (true) queue.take().run();
                } catch (InterruptedException ignored) {}
            });
            workers[i].start();
        }
    }

    public void submit(Runnable task) { queue.add(task); }

    public static void main(String[] args) {
        MiniThreadPool pool = new MiniThreadPool(3);
        for (int i = 1; i <= 6; i++) {
            int finalI = i;
            pool.submit(() -> System.out.println("Task " + finalI +
                                " run by " + Thread.currentThread().getName()));
        }
    }
}
