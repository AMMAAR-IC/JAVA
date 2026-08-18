import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DuplicateWordsIgnoreCase {

    public static void main(String[] args){

        String text=
            "Java java STREAM stream API api Code";

        Arrays.stream(
            text.split("\\s+")
        )
        .map(
            String::toLowerCase
        )
        .collect(
            Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            )
        )
        .entrySet()
        .stream()
        .filter(
            e -> e.getValue()>1
        )
        .forEach(
            System.out::println
        );
    }
}
