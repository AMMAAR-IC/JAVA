import java.util.*;

public class LSMCompaction {

    static List<Map<String,String>> levels = new ArrayList<>();

    static void compact(){
        Map<String,String> merged = new HashMap<>();
        for(Map<String,String> m : levels)
            merged.putAll(m);

        levels.clear();
        levels.add(merged);
    }

    public static void main(String[] args){
        levels.add(Map.of("A","1"));
        levels.add(Map.of("A","2","B","3"));

        compact();
        System.out.println(levels);
    }
}
