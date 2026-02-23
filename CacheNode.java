import java.util.*;

public class CacheNode {
    static Map<String,String> cache=new HashMap<>();

    static void put(String k,String v){
        cache.put(k,v);
    }

    static String get(String k){
        return cache.get(k);
    }

    public static void main(String[] args){
        put("user","ammaar");
        System.out.println(get("user"));
    }
}
