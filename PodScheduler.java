import java.util.*;

public class PodScheduler {
    static Map<String,Integer> nodes=Map.of(
            "node1",4,
            "node2",2,
            "node3",6);

    public static void main(String[] args){
        String best = nodes.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();

        System.out.println("Scheduled on "+best);
    }
}
