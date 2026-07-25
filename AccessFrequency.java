import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class AccessFrequency {

    public static void main(String[] args){

        List<String> accesses=
            List.of(
                "A","B","A","C",
                "B","A","D","A"
            );

        accesses.stream()
                .collect(
                    Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                    )
                )
                .forEach(
                    System.out::println
                );
    }
}
