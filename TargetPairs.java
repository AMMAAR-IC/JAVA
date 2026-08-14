import java.util.*;
import java.util.stream.*;

public class TargetPairs {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(2,4,3,5,7,8,1);

        int target=9;

        IntStream.range(
                    0,
                    nums.size()
                 )
                 .boxed()
                 .flatMap(
                     i ->
                     IntStream.range(
                         i+1,
                         nums.size()
                     )
                     .filter(
                         j ->
                         nums.get(i)
                         +nums.get(j)
                         ==target
                     )
                     .mapToObj(
                         j ->
                         nums.get(i)
                         +" + "
                         +nums.get(j)
                     )
                 )
                 .forEach(
                     System.out::println
                 );
    }
}
