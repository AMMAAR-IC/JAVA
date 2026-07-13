import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DuplicateCharacters {

    public static void main(String[] args){

        String text="mississippi";

        text.chars()
            .mapToObj(
                c->(char)c
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
                e->e.getValue()>1
            )
            .forEach(
                System.out::println
            );
    }
}
