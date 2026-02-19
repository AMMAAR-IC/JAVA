import java.util.*;

public class SimpleThreadPool {
    Queue<Runnable> q=new LinkedList<>();

    public SimpleThreadPool(int n){
        for(int i=0;i<n;i++){
            new Thread(() -> {
                while(true){
                    Runnable r;
                    synchronized(q){
                        while(q.isEmpty()){
                            try{ q.wait(); }catch(Exception ignored){}
                        }
                        r=q.poll();
                    }
                    r.run();
                }
            }).start();
        }
    }

    public void submit(Runnable r){
        synchronized(q){
            q.add(r);
            q.notify();
        }
    }

    public static void main(String[] args){
        SimpleThreadPool p=new SimpleThreadPool(3);
        for(int i=0;i<5;i++){
            int id=i;
            p.submit(() -> System.out.println("Task "+id));
        }
    }
}
