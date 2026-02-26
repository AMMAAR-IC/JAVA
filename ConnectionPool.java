import java.net.*;
import java.util.*;

public class ConnectionPool {
    static Queue<Socket> pool=new LinkedList<>();

    static Socket get() throws Exception{
        if(!pool.isEmpty()) return pool.poll();
        return new Socket("example.com",80);
    }

    static void release(Socket s){
        pool.add(s);
    }
}
