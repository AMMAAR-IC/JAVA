import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class CommonWordPairs {

    public static void main(String[] args){

        String text=
            "java stream java stream api java stream";

        String[] words=
            text.split(" ");

        IntStream.range(
                    0,
                    words.length-1
                 )
                 .mapToObj(
                     i ->
                     words[i]
                     +" "
                     +words[i+1]
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
                     Map.Entry.comparingByValue()
                 )
                 .ifPresent(
                     System.out::println
                 );
    }
}
