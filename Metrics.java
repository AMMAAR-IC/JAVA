import java.util.*;

public class Metrics {
    static Map<String,Long> counters = new HashMap<>();

    static void inc(String k){ counters.put(k, counters.getOrDefault(k,0L)+1); }

    public static void main(String[] args){
        inc("requests"); inc("requests");
        System.out.println(counters);
    }
}
