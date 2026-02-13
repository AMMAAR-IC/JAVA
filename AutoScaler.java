import java.util.concurrent.*;

public class AutoScaler {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );

        for(int i=0;i<50;i++){
            int id=i;
            pool.submit(() -> {
                try { Thread.sleep(500); } catch(Exception ignored){}
                System.out.println("Job " + id);
            });

            if(pool.getQueue().size() > 10)
                pool.setCorePoolSize(8);
        }

        pool.shutdown();
    }
}
