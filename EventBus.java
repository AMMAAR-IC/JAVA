import java.util.*;
import java.util.function.Consumer;

public class EventBus {
    private final Map<String, List<Consumer<String>>> listeners = new HashMap<>();

    public void subscribe(String topic, Consumer<String> listener) {
        listeners.computeIfAbsent(topic, k -> new ArrayList<>()).add(listener);
    }

    public void publish(String topic, String msg) {
        for (Consumer<String> l : listeners.getOrDefault(topic, List.of()))
            l.accept(msg);
    }

    public static void main(String[] args) {
        EventBus bus = new EventBus();
        bus.subscribe("news", msg -> System.out.println("Reader1 got: " + msg));
        bus.subscribe("news", msg -> System.out.println("Reader2 got: " + msg));
        bus.publish("news", "Java 22 released!");
    }
}
