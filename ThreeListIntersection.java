import java.util.*;

public class ThreeListIntersection {

    public static void main(String[] args){

        List<Integer> a=
            List.of(1,2,3,4,5);

        List<Integer> b=
            List.of(2,3,4,6);

        List<Integer> c=
            List.of(3,4,7,8);

        a.stream()
         .filter(b::contains)
         .filter(c::contains)
         .forEach(
             System.out::println
         );
    }
}
