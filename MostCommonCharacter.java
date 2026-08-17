import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MostCommonCharacter {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "banana",
                "mississippi",
                "programming"
            );

        words.stream()
             .map(
                 word -> {

                     Character character=

                         word.chars()
                             .mapToObj(
                                 c -> (char)c
                             )
                             .collect(
                                 Collectors.groupingBy(
                                     Function.identity(),
                                     Collectors.counting()
                                 )
                             )
                             .entrySet()
                             .stream()
                             .max(
                                 Map.Entry
                                 .comparingByValue()
                             )
                             .map(
                                 Map.Entry::getKey
                             )
                             .orElse(null);

                     return word+" -> "+character;
                 }
             )
             .forEach(
                 System.out::println
             );
    }
}
