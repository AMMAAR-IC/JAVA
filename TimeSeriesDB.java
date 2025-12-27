import java.util.*;

public class TimeSeriesDB {
    static Map<String,List<Long>> store=new HashMap<>();

    static void add(String key,long v){
        store.computeIfAbsent(key,k->new ArrayList<>()).add(v);
    }

    static List<Long> last(String key,int n){
        var l=store.get(key);
        return l.subList(Math.max(0,l.size()-n), l.size());
    }

    public static void main(String[] args){
        add("cpu",50); add("cpu",55); add("cpu",60);
        System.out.println(last("cpu",2));
    }
}
