import java.util.concurrent.*;

public class MiniKVStore {
    static ConcurrentHashMap<String, String> db = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        put("name","Ammaar");
        put("lang","Java");

        System.out.println(get("name"));
        System.out.println(keys());
    }

    static void put(String k,String v){ db.put(k,v); }
    static String get(String k){ return db.get(k); }
    static Object keys(){ return db.keySet(); }
}
