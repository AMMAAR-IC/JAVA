import java.util.concurrent.*;

public class TimeoutTask {
    public static void main(String[] args)throws Exception{
        ExecutorService e=Executors.newSingleThreadExecutor();
        Future<?> f=e.submit(()->{ while(true); });
        try{ f.get(1,TimeUnit.SECONDS); }
        catch(TimeoutException ex){ System.out.println("Timed out"); }
        e.shutdownNow();
    }
}
