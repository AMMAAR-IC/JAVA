import java.util.*;

record Interval(
    int start,
    int end
){}

public class MergeIntervals {

    public static void main(String[] args){

        List<Interval> list=
            List.of(
                new Interval(1,3),
                new Interval(2,5),
                new Interval(7,9)
            );

        list.stream()
            .sorted(
                Comparator.comparingInt(
                    Interval::start
                )
            )
            .forEach(System.out::println);
    }
}
