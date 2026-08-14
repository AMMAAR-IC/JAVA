import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DocumentFrequency {

    public static void main(String[] args){

        List<String> documents=
            List.of(
                "java streams are powerful",
                "java streams are fast",
                "python is powerful"
            );

        Map<String,Long> result=

            documents.stream()
                     .flatMap(
                         doc ->
                         Arrays.stream(
                             doc.split(" ")
                         )
                     )
                     .collect(
                         Collectors.groupingBy(
                             Function.identity(),
                             Collectors.counting()
                         )
                     );

        System.out.println(result);
    }
}
