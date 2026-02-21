import java.util.*;

public class ProducerConsumer {
    static Queue<Integer> q = new LinkedList<>();
    static final int MAX = 5;

    public static void main(String[] args) {

        new Thread(() -> {
            int x = 0;
            while(true){
                synchronized(q){
                    try{
                        while(q.size()==MAX) q.wait();
                        q.add(x++);
                        System.out.println("Produced");
                        q.notifyAll();
                    }catch(Exception ignored){}
                }
            }
        }).start();

        new Thread(() -> {
            while(true){
                synchronized(q){
                    try{
                        while(q.isEmpty()) q.wait();
                        System.out.println("Consumed "+q.poll());
                        q.notifyAll();
                    }catch(Exception ignored){}
                }
            }
        }).start();
    }
}
