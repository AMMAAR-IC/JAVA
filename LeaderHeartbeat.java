import java.util.*;

public class LeaderHeartbeat {
    static Map<String,Long> nodes = new HashMap<>();

    static void heartbeat(String n){
        nodes.put(n,System.currentTimeMillis());
    }

    static void check(){
        long now=System.currentTimeMillis();
        nodes.forEach((k,v)->{
            if(now-v>3000)
                System.out.println(k+" DEAD");
        });
    }

    public static void main(String[] args)throws Exception{
        heartbeat("node1");
        Thread.sleep(4000);
        check();
    }
}
