import java.util.*;

public class Median {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(
                7,1,9,5,3
            );

        List<Integer> sorted=
            nums.stream()
                .sorted()
                .toList();

        int median=
            sorted.get(
                sorted.size()/2
            );

        System.out.println(median);
    }
}
