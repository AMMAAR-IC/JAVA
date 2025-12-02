import java.util.concurrent.*;
import java.util.*;

public class ParallelPrimes extends RecursiveTask<List<Integer>> {
    int start,end;
    ParallelPrimes(int s,int e){start=s;end=e;}

    protected List<Integer> compute(){
        if (end-start < 10000){
            List<Integer> res=new ArrayList<>();
            for(int i=start;i<end;i++) if(isPrime(i)) res.add(i);
            return res;
        }
        int mid=(start+end)/2;
        var left=new ParallelPrimes(start,mid);
        var right=new ParallelPrimes(mid,end);
        left.fork();
        List<Integer> r=right.compute();
        r.addAll(left.join());
        return r;
    }

    static boolean isPrime(int n){
        if(n<2)return false;
        for(int i=2;i*i<=n;i++) if(n%i==0) return false;
        return true;
    }

    public static void main(String[] args){
        ForkJoinPool p=new ForkJoinPool();
        List<Integer> primes=p.invoke(new ParallelPrimes(2,100000));
        System.out.println("Total primes: "+primes.size());
    }
}
