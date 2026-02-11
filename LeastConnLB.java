import java.util.*;

public class LeastConnLB {
    static Map<String,Integer> conn = new HashMap<>();

    static String pick(){
        return conn.entrySet().stream()
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .get().getKey();
    }

    public static void main(String[] args){
        conn.put("S1",5);
        conn.put("S2",2);
        conn.put("S3",7);

        System.out.println("Chosen: " + pick());
    }
}
