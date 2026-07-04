import java.util.*;

public class AverageEvenNumbers {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(2,4,5,6,7,8);

        double avg=
            nums.stream()
                .filter(n->n%2==0)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.println(avg);
    }
}
