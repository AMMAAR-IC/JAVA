import java.util.*;
import java.util.stream.*;

public class ListToSet {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(1,2,2,3,4,4,5);

        Set<Integer> set=
            nums.stream()
                .collect(Collectors.toSet());

        System.out.println(set);
    }
}
