import java.util.*;
import java.util.stream.*;

public class LongestConsecutive {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(
                100,4,200,1,3,2
            );

        Set<Integer> set=
            new HashSet<>(nums);

        int longest=

            nums.stream()
                .filter(
                    n -> !set.contains(n-1)
                )
                .mapToInt(
                    start -> {

                        int current=start;
                        int length=1;

                        while(
                            set.contains(current+1)
                        ){
                            current++;
                            length++;
                        }

                        return length;
                    }
                )
                .max()
                .orElse(0);

        System.out.println(longest);
    }
}
