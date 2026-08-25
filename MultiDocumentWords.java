import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MultiDocumentWords {

    public static void main(String[] args){

        List<String> documents=
            List.of(
                "java stream api",
                "java backend development",
                "python api development"
            );

        Map<String,Long> result=

            documents.stream()
                     .flatMap(
                         doc ->
                         Arrays.stream(
                             doc.split(" ")
                         )
                         .distinct()
                     )
                     .collect(
                         Collectors.groupingBy(
                             Function.identity(),
                             Collectors.counting()
                         )
                     );

        result.entrySet()
              .stream()
              .filter(
                  e->e.getValue()>1
              )
              .forEach(
                  System.out::println
              );
    }
}
