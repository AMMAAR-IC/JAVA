import java.util.*;
import java.util.stream.*;

public class ConcatenateLists {

    public static void main(String[] args){

        List<Integer> a=
            List.of(1,2,3);

        List<Integer> b=
            List.of(4,5,6);

        Stream.concat(
                a.stream(),
                b.stream()
        ).forEach(System.out::println);
    }
}
