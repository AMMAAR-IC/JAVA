import java.util.*;

public class ExceptOperation {

    public static void main(String[] args){

        List<Integer> list1=
            List.of(1,2,3,4,5);

        List<Integer> list2=
            List.of(2,4);

        list1.stream()
             .filter(
                 n->!list2.contains(n)
             )
             .forEach(System.out::println);
    }
}
