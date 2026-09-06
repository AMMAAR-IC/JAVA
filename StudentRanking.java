import java.util.*;
import java.util.stream.*;

record Score(
    String student,
    int marks
){}

public class StudentRanking {

    public static void main(String[] args){

        List<Score> scores =
            List.of(
                new Score("Alex",80),
                new Score("Alex",90),

                new Score("Sam",70),
                new Score("Sam",95),

                new Score("John",85),
                new Score("John",88)
            );

        scores.stream()
              .collect(
                  Collectors.groupingBy(
                      Score::student,
                      Collectors.averagingInt(
                          Score::marks
                      )
                  )
              )
              .entrySet()
              .stream()
              .sorted(
                  Map.Entry
                     .<String,Double>
                     comparingByValue()
                     .reversed()
              )
              .forEach(
                  System.out::println
              );
    }
}
