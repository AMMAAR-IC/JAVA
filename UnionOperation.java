import java.util.*;
import java.util.stream.*;

public class UnionOperation {

    public static void main(String[] args){

        List<Integer> list1=
            List.of(1,2,3,4);

        List<Integer> list2=
            List.of(3,4,5,6);

        List<Integer> result=

            Stream.concat(
                    list1.stream(),
                    list2.stream()
            )
            .distinct()
            .sorted()
            .toList();

        System.out.println(result);
    }
}
