import java.util.*;

public class MiniDI {
    private final Map<Class<?>, Object> registry = new HashMap<>();

    public <T> void register(Class<T> type, T instance) { registry.put(type, instance); }
    public <T> T resolve(Class<T> type) { return type.cast(registry.get(type)); }

    public static void main(String[] args) {
        MiniDI di = new MiniDI();
        di.register(String.class, "Hello DI");
        di.register(Integer.class, 42);

        System.out.println(di.resolve(String.class));
        System.out.println(di.resolve(Integer.class));
    }
}
