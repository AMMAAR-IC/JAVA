import java.util.*;

public class LRUCache<K,V> {
    private final int capacity;
    private final Map<K,V> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return size() > LRUCache.this.capacity;
            }
        };
    }

    public synchronized V get(K key) { return map.get(key); }
    public synchronized void put(K key, V value) { map.put(key, value); }

    public synchronized String toString() { return map.toString(); }

    public static void main(String[] args) {
        LRUCache<Integer,String> cache = new LRUCache<>(3);
        cache.put(1,"A"); cache.put(2,"B"); cache.put(3,"C");
        System.out.println(cache);  // {1=A, 2=B, 3=C}
        cache.get(1); cache.put(4,"D");
        System.out.println(cache);  // {3=C, 1=A, 4=D}
    }
}
