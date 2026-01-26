import java.util.concurrent.*;

public class RateLimitedExecutor {
    static Semaphore sem = new Semaphore(2); // 2 tasks per second

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService refill = Executors.newSingleThreadScheduledExecutor();
        refill.scheduleAtFixedRate(() -> {
            sem.drainPermits();
            sem.release(2);
        }, 0, 1, TimeUnit.SECONDS);

        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int id = i;
            pool.submit(() -> {
                try {
                    sem.acquire();
                    System.out.println("Task " + id);
                } catch (Exception ignored) {}
            });
        }
    }
}
