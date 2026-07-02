import java.util.*;

public class DuplicateElements {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(1,2,3,2,4,5,1,6,3);

        Set<Integer> seen=
            new HashSet<>();

        nums.stream()
            .filter(n->!seen.add(n))
            .distinct()
            .forEach(System.out::println);
    }
}
