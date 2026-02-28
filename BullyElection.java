import java.util.*;

public class BullyElection {
    static List<Integer> nodes=List.of(1,2,3,4,5);

    public static void main(String[] args){
        int starter=3;

        int leader=nodes.stream()
                .filter(n->n>starter)
                .max(Integer::compare)
                .orElse(starter);

        System.out.println("Leader: "+leader);
    }
}
