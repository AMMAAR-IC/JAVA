import java.util.concurrent.*;

public class AsyncLogger {
    static BlockingQueue<String> q = new LinkedBlockingQueue<>();

    static {
        new Thread(() -> {
            try {
                while (true)
                    System.out.println(q.take());
            } catch (Exception ignored) {}
        }).start();
    }

    static void log(String s){ q.offer(s); }

    public static void main(String[] args){
        log("hello");
        log("world");
    }
}
