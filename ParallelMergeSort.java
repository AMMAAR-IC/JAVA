// ParallelMergeSort.java
import java.util.concurrent.*;

public class ParallelMergeSort {
    static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        int[] arr = new java.util.Random().ints(100000).toArray();
        sort(arr, 0, arr.length-1);
        pool.shutdown();
        System.out.println("Sorted.");
    }

    static void sort(int[] a, int l, int r) throws Exception {
        if (l >= r) return;
        int m = (l+r)/2;

        Future<?> left = pool.submit(() -> {
            try { sort(a, l, m); } catch (Exception ignored) {}
        });
        Future<?> right = pool.submit(() -> {
            try { sort(a, m+1, r); } catch (Exception ignored) {}
        });

        left.get(); right.get();
        merge(a,l,m,r);
    }

    static void merge(int[] a,int l,int m,int r){
        int[] tmp = new int[r-l+1];
        int i=l,j=m+1,k=0;
        while(i<=m || j<=r){
            if(j>r || (i<=m && a[i]<a[j])) tmp[k++]=a[i++];
            else tmp[k++]=a[j++];
        }
        System.arraycopy(tmp,0,a,l,tmp.length);
    }
}
