import java.util.*;
import java.util.stream.*;

public class MissingIds {

    public static void main(String[] args){

        List<Integer> ids=
            List.of(
                1001,
                1002,
                1004,
                1006
            );

        IntStream.rangeClosed(
                    ids.get(0),
                    ids.get(ids.size()-1)
                 )
                 .filter(
                     id->!ids.contains(id)
                 )
                 .forEach(System.out::println);
    }
}s
