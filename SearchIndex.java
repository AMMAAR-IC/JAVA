import java.util.*;
import java.util.stream.*;

public class SearchIndex {

    public static void main(String[] args){

        List<String> docs=
            List.of(
                "java streams",
                "spring boot",
                "java concurrency"
            );

        Map<String,Set<Integer>> index=

            IntStream.range(0,docs.size())
                     .boxed()
                     .flatMap(i->

                         Arrays.stream(
                             docs.get(i).split(" ")
                         )
                         .map(
                             word->Map.entry(
                                 word,
                                 i
                             )
                         )

                     )
                     .collect(
                         Collectors.groupingBy(
                             Map.Entry::getKey,
                             Collectors.mapping(
                                 Map.Entry::getValue,
                                 Collectors.toSet()
                             )
                         )
                     );

        System.out.println(index);
    }
}
