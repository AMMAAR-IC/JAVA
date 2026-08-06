import java.util.*;

public class IntersectOperation {

    public static void main(String[] args){

        List<Integer> list1=
            List.of(1,2,3,4,5);

        List<Integer> list2=
            List.of(4,5,6,7);

        list1.stream()
             .filter(list2::contains)
             .distinct()
             .forEach(System.out::println);
    }
}
