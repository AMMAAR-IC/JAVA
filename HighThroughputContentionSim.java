import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class HighThroughputContentionSim {

    static class Node {
        volatile long value;
        volatile Node next;
        
        private static final VarHandle VALUE_HANDLE;
        static {
            try {
                VALUE_HANDLE = MethodHandles.lookup().findVarHandle(Node.class, "value", long.class);
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }

        Node(long value) {
            this.value = value;
        }

        boolean compareAndSwap(long expected, long newValue) {
            return VALUE_HANDLE.compareAndSet(this, expected, newValue);
        }
    }

    private final Node[] slots;
    private final LongAdder collisions = new LongAdder();

    public HighThroughputContentionSim(int capacity) {
        this.slots = new Node[capacity];
        for (int i = 0; i < capacity; i++) {
            slots[i] = new Node(0);
        }
    }

    public void update(int index, long delta) {
        Node node = slots[index % slots.length];
        while (true) {
            long current = node.value;
            if (node.compareAndSwap(current, current + delta)) {
                break;
            }
            collisions.increment();
            Thread.onSpinWait();
        }
    }

    public static void main(String[] args) {
        int threads = 8;
        int iterations = 1_000_000;
        HighThroughputContentionSim sim = new HighThroughputContentionSim(16);

        long start = System.nanoTime();
        
        IntStream.range(0, threads).parallel().forEach(t -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < iterations; i++) {
                sim.update(random.nextInt(32), 1);
            }
        });

        long end = System.nanoTime();
        
        long totalValue = 0;
        for (Node n : sim.slots) totalValue += n.value;

        System.out.printf("Processed %d updates in %.2fms%n", totalValue, (end - start) / 1e6);
        System.out.printf("CAS Failures (Contention): %d%n", sim.collisions.sum());
    }
}
