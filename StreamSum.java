import java.util.*;

public class StreamSum {

    public static void main(String[] args){

        List<Integer> list=List.of(1,2,3,4,5);

        int sum = list.stream()
                      .mapToInt(Integer::intValue)
                      .sum();

        System.out.println(sum);
    }
}
