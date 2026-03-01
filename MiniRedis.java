import java.util.*;

public class MiniRedis {
    static Map<String,String> db = new HashMap<>();

    static void set(String k,String v){ db.put(k,v); }
    static String get(String k){ return db.get(k); }

    public static void main(String[] args){
        set("name","ammaar");
        System.out.println(get("name"));
    }
}
