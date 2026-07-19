import java.util.*;
import java.util.stream.*;

public class MissingNumbers {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(1,2,4,6,8,9);

        IntStream.rangeClosed(
                    Collections.min(nums),
                    Collections.max(nums)
                 )
                 .filter(
                     n->!nums.contains(n)
                 )
                 .forEach(
                     System.out::println
                 );
    }
}
