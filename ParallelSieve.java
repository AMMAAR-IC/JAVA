// ParallelSieve.java
import java.util.concurrent.*;
import java.util.*;

public class ParallelSieve {
    public static List<Integer> sieve(int n) {
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true); prime[0]=prime[1]=false;
        int limit = (int)Math.sqrt(n);
        ForkJoinPool pool = new ForkJoinPool();
        for (int p=2;p<=limit;p++) if (prime[p]) {
            final int fp = p;
            pool.submit(() -> {
                for (int k=fp*fp;k<=n;k+=fp) prime[k]=false;
            });
        }
        pool.shutdown();
        try { pool.awaitTermination(10, TimeUnit.SECONDS); } catch(Exception ignored){}
        List<Integer> res = new ArrayList<>();
        for (int i=2;i<=n;i++) if (prime[i]) res.add(i);
        return res;
    }
    public static void main(String[] args) { System.out.println(sieve(100000)); }
}
