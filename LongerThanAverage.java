import java.util.*;

public class LongerThanAverage {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "java",
                "stream",
                "api",
                "microservice"
            );

        double avg=
            words.stream()
                 .mapToInt(
                     String::length
                 )
                 .average()
                 .orElse(0);

        words.stream()
             .filter(
                 s->s.length()>avg
             )
             .forEach(System.out::println);
    }
}
