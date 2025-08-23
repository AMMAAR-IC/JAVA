import java.util.concurrent.atomic.AtomicLong;

public class RateLimiter {
    private final long capacity, refillRate;
    private final AtomicLong tokens = new AtomicLong();
    private long lastRefill;

    public RateLimiter(long capacity, long refillRatePerSec) {
        this.capacity = capacity;
        this.refillRate = refillRatePerSec;
        this.tokens.set(capacity);
        this.lastRefill = System.nanoTime();
    }

    public synchronized boolean allow() {
        long now = System.nanoTime();
        long delta = (now - lastRefill) / 1_000_000_000;
        if (delta > 0) {
            long refill = delta * refillRate;
            tokens.set(Math.min(capacity, tokens.get() + refill));
            lastRefill = now;
        }
        if (tokens.get() > 0) {
            tokens.decrementAndGet();
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        RateLimiter rl = new RateLimiter(5, 1);
        for (int i = 0; i < 10; i++) {
            System.out.println("Call " + i + " allowed? " + rl.allow());
            Thread.sleep(300);
        }
    }
}
