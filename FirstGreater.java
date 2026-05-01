import java.util.*;

public class FirstGreater {

    public static void main(String[] args){

        List<Integer> list=List.of(3,7,12,5);

        int val = list.stream()
                      .filter(n->n>10)
                      .findFirst()
                      .orElse(-1);

        System.out.println(val);
    }
}
