import java.util.concurrent.*;

public class ThreadPoolMeter {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 6, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );

        for(int i=0;i<50;i++){
            pool.submit(() -> {
                try{ Thread.sleep(500); }catch(Exception ignored){}
            });
            System.out.println("Queue: " + pool.getQueue().size() +
                    " Active: " + pool.getActiveCount());
        }
        pool.shutdown();
    }
}
