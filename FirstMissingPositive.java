import java.util.*;
import java.util.stream.*;

public class FirstMissingPositive {

    public static void main(String[] args){

        List<Integer> nums =
            List.of(
                3,4,-1,1
            );

        Set<Integer> set =
            nums.stream()
                .filter(n -> n > 0)
                .collect(
                    Collectors.toSet()
                );

        int result =

            IntStream.iterate(
                        1,
                        n -> n + 1
                    )
                    .filter(
                        n -> !set.contains(n)
                    )
                    .findFirst()
                    .orElse(-1);

        System.out.println(result);
    }
}
