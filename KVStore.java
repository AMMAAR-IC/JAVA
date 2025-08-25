import java.util.*;

public class KVStore {
    private final Map<String, String> db = new HashMap<>();

    public void put(String key, String value) { db.put(key, value); }
    public String get(String key) { return db.get(key); }
    public void delete(String key) { db.remove(key); }

    public static void main(String[] args) {
        KVStore store = new KVStore();
        store.put("user:1", "Ammaar");
        store.put("user:2", "Batman");
        System.out.println(store.get("user:1"));
        store.delete("user:2");
    }
}
