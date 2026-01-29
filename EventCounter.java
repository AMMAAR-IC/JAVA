import java.util.*;

public class EventCounter {
    static Queue<Long> q=new LinkedList<>();

    static int hit(){
        long now=System.currentTimeMillis();
        q.add(now);
        while(now-q.peek()>1000) q.poll();
        return q.size();
    }

    public static void main(String[] args)throws Exception{
        for(int i=0;i<10;i++){
            System.out.println(hit());
            Thread.sleep(200);
        }
    }
}
