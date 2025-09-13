// SimpleScheduler.java
import java.util.*;
import java.util.concurrent.*;
import java.time.*;

public class SimpleScheduler {
    private final ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);

    // run after delaySeconds
    public void scheduleDelayed(Runnable r, long delaySeconds) {
        pool.schedule(r, delaySeconds, TimeUnit.SECONDS);
    }

    // run repeatedly every intervalSeconds
    public void scheduleFixed(Runnable r, long initialDelay, long intervalSeconds) {
        pool.scheduleAtFixedRate(r, initialDelay, intervalSeconds, TimeUnit.SECONDS);
    }

    // run at a specific LocalTime each day
    public void scheduleDaily(Runnable r, LocalTime time) {
        long initial = computeInitialDelay(time);
        long day = 24 * 60 * 60;
        pool.scheduleAtFixedRate(r, initial, day, TimeUnit.SECONDS);
    }

    private long computeInitialDelay(LocalTime time) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.with(time);
        if (now.isAfter(next)) next = next.plusDays(1);
        return Duration.between(now, next).getSeconds();
    }

    public void shutdown() { pool.shutdown(); }

    public static void main(String[] args) throws Exception {
        SimpleScheduler s = new SimpleScheduler();
        s.scheduleFixed(() -> System.out.println("Tick: " + new Date()), 1, 5);
        s.scheduleDaily(() -> System.out.println("Daily job at " + LocalTime.now()), LocalTime.now().plusSeconds(10));
        Thread.sleep(30000);
        s.shutdown();
    }
}
