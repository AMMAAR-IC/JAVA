// MonteCarloPi.java
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;

public class MonteCarloPi {
    public static void main(String[] args) throws Exception {
        int threads = Runtime.getRuntime().availableProcessors();
        long samplesPerThread = 5_000_000L;
        ExecutorService ex = Executors.newFixedThreadPool(threads);
        AtomicLong inside = new AtomicLong();
        List<Future<?>> futures = new ArrayList<>();
        for (int t=0;t<threads;t++) {
            futures.add(ex.submit(() -> {
                Random r = new Random();
                long local = 0;
                for (long i=0;i<samplesPerThread;i++) {
                    double x = r.nextDouble(), y = r.nextDouble();
                    if (x*x + y*y <= 1.0) local++;
                }
                inside.addAndGet(local);
            }));
        }
        for (var f : futures) f.get();
        ex.shutdown();
        double pi = 4.0 * inside.get() / (samplesPerThread * threads);
        System.out.println("Estimated π = " + pi);
    }
}
