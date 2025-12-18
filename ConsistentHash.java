import java.util.*;

public class ConsistentHash {
    static TreeMap<Integer, String> ring = new TreeMap<>();

    static void addNode(String node) {
        ring.put(node.hashCode(), node);
    }

    static String getNode(String key) {
        int h = key.hashCode();
        if (!ring.containsKey(h))
            h = ring.ceilingKey(h) != null ? ring.ceilingKey(h) : ring.firstKey();
        return ring.get(h);
    }

    public static void main(String[] args) {
        addNode("NodeA");
        addNode("NodeB");
        addNode("NodeC");

        System.out.println(getNode("user123"));
    }
}
