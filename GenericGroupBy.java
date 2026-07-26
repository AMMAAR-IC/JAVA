import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class GenericGroupBy {

    public static <T,K>
    Map<K,List<T>> groupBy(
            List<T> list,
            Function<T,K> classifier){

        return list.stream()
                   .collect(
                       Collectors.groupingBy(
                           classifier
                       )
                   );
    }

    public static void main(String[] args){

        List<String> words=
            List.of(
                "apple",
                "ant",
                "banana",
                "boat"
            );

        System.out.println(

            groupBy(
                words,
                s->s.charAt(0)
            )

        );
    }
}
