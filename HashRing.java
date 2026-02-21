import java.util.*;

public class HashRing {
    TreeMap<Integer,String> ring = new TreeMap<>();

    void add(String node){
        ring.put(node.hashCode(), node);
    }

    String get(String key){
        var tail = ring.tailMap(key.hashCode());
        return tail.isEmpty()
                ? ring.firstEntry().getValue()
                : tail.firstEntry().getValue();
    }

    public static void main(String[] args){
        HashRing h=new HashRing();
        h.add("Server1");
        h.add("Server2");
        System.out.println(h.get("user42"));
    }
}
