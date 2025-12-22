import java.util.concurrent.atomic.AtomicLong;

public class TimeUUID {
    static AtomicLong seq = new AtomicLong();

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        long id = (time << 16) | (seq.incrementAndGet() & 0xFFFF);
        System.out.println(Long.toHexString(id));
    }
}
