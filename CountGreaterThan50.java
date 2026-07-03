import java.util.*;

public class CountGreaterThan50 {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(10,55,60,30,75,40);

        long count=
            nums.stream()
                .filter(n->n>50)
                .count();

        System.out.println(count);
    }
}
