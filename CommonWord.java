import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class CommonWord {

    public static void main(String[] args){

        List<String> docs=
            List.of(
                "java stream api",
                "java collections stream",
                "stream java backend"
            );

        docs.stream()
            .flatMap(
                s->Arrays.stream(
                    s.split(" ")
                )
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
