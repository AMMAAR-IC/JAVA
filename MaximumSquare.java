import java.util.*;

public class MaximumSquare {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(
                2,
                5,
                8,
                10
            );

        int result=
            nums.stream()
                .map(n->n*n)
                .max(Integer::compare)
                .orElse(0);

        System.out.println(result);
    }
}
