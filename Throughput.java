import java.util.*;

public class Throughput {
    static Queue<Long> q=new LinkedList<>();

    static void hit(){
        long now=System.currentTimeMillis();
        q.add(now);
        while(now-q.peek()>1000) q.poll();
        System.out.println("TPS="+q.size());
    }

    public static void main(String[] args)throws Exception{
        for(int i=0;i<10;i++){
            hit();
            Thread.sleep(150);
        }
    }
}
