import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FirstUniqueWord {

    public static void main(String[] args){

        String sentence=
            "java stream java code stream api";

        String result=

            Arrays.stream(
                sentence.split(" ")
            )
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    LinkedHashMap::new,
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .filter(
                e -> e.getValue()==1
            )
            .map(
                Map.Entry::getKey
            )
            .findFirst()
            .orElse(
                "Not Found"
            );

        System.out.println(result);
    }
}
