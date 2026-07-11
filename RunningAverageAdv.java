import java.util.*;
import java.util.concurrent.atomic.*;

public class RunningAverageAdv {

    public static void main(String[] args){

        AtomicInteger sum=
            new AtomicInteger();

        AtomicInteger count=
            new AtomicInteger();

        List<Integer> nums=
            List.of(
                10,20,30,40,50
            );

        nums.stream()
            .map(n->

                sum.addAndGet(n) /
                (double)count.incrementAndGet()

            )
            .forEach(System.out::println);
    }
}
