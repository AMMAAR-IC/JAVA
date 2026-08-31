import java.util.*;
import java.util.stream.*;

public class MissingIdsAdvanced {

    public static void main(String[] args){

        List<Integer> ids=
            List.of(
                101,
                102,
                105,
                106,
                109
            );

        Set<Integer> existing=
            new HashSet<>(ids);

        IntStream.rangeClosed(
                    Collections.min(ids),
                    Collections.max(ids)
                 )
                 .filter(
                     id -> !existing.contains(id)
                 )
                 .forEach(
                     System.out::println
                 );
    }
}
