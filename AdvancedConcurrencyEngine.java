import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

public class AdvancedConcurrencyEngine {
  
    static class Task {
        final String name;
        final Runnable action;
        Task next; 

        Task(String name, Runnable action) {
            this.name = name;
            this.action = action;
        }
    }

    private final AtomicReference<Task> stackHead = new AtomicReference<>();

    public void submit(String name, Runnable action) {
        Task newTask = new Task(name, action);
        Task currentHead;
        do {
            currentHead = stackHead.get();
            newTask.next = currentHead;
        } while (!stackHead.compareAndSet(currentHead, newTask));
        System.out.println("[Queued]: " + name);
    }

    private Task tryPop() {
        Task currentHead;
        Task nextHead;
        do {
            currentHead = stackHead.get();
            if (currentHead == null) return null;
            nextHead = currentHead.next;
        } while (!stackHead.compareAndSet(currentHead, nextHead));
        return currentHead;
    }

    public void startWorker(int id) {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Task task = tryPop();
                if (task != null) {
                    System.out.println("Worker-" + id + " executing: " + task.name);
                    task.action.run();
                } else {

                    LockSupport.parkNanos(1_000_000); 
                }
            }
        });
        worker.setDaemon(true);
        worker.start();
    }

    public static void main(String[] args) throws InterruptedException {
        AdvancedConcurrencyEngine engine = new AdvancedConcurrencyEngine();

        for (int i = 1; i <= 3; i++) engine.startWorker(i);

        Runnable producer = () -> {
            for (int i = 0; i < 5; i++) {
                String taskName = Thread.currentThread().getName() + "-Task-" + i;
                engine.submit(taskName, () -> {
                    try { Thread.sleep(10); } catch (InterruptedException e) {}
                });
            }
        };

        Thread p1 = new Thread(producer, "P1");
        Thread p2 = new Thread(producer, "P2");

        p1.start();
        p2.start();

        p1.join();
        p2.join();

        Thread.sleep(1000);
        System.out.println("Execution Complete.");
    }
}
