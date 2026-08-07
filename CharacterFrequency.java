import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class CharacterFrequency {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "java",
                "stream"
            );

        words.stream()
             .flatMap(
                 s->s.chars()
                     .mapToObj(
                         c->(char)c
                     )
             )
             .collect(
                 Collectors.groupingBy(
                     Function.identity(),
                     Collectors.counting()
                 )
             )
             .forEach(System.out::println);
    }
}
